package com.xutonghuai.latter.app;


import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;

/**
 * 配置文件的获取和存储
 * Created by xutonghuai on 2018-03-14.
 */
public class Configurator {

    /**
     * WeakHashMap在键值对不适用的时候，可以回收资源，这里不适合用这个
     */
    private static final HashMap<Object,Object> LATTER_CONFIGS = new HashMap<>();

    private static final List<IconFontDescriptor> ICONS = new ArrayList<>();

    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private static final Handler HANDLER = new Handler();

    public static Configurator getInstance(){
        return  Holder.INSTANCE;
    }

    private Configurator() {
        //初始话配置未准备完成
        LATTER_CONFIGS.put(ConfigKeys.CONFIG_READY,false);
        LATTER_CONFIGS.put(ConfigKeys.HANDLER,HANDLER);
    }

    private static final class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<Object,Object> getLatterConfigs(){
        return LATTER_CONFIGS;
    }

    public final void configure(){
        initIcons();
        //配置准备完成
        LATTER_CONFIGS.put(ConfigKeys.CONFIG_READY,true);
    }

    public final Configurator withApiHost(String host){
        LATTER_CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }

    /**
     * 添加字体库
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 添加拦截器
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTER_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    /**
     * 添加拦截器
     * @return
     */
    public final Configurator withInterceptor(ArrayList<Interceptor> interceptor){
        INTERCEPTORS.addAll(interceptor);
        LATTER_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    private void initIcons(){
        if(ICONS.size()>0){
            //初始化字体库
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for(int i=1;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 检察配置是否准备完成
     */
    private void chechConfigurator(){
        final boolean isReady = (boolean) LATTER_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if(!isReady){
           throw  new RuntimeException("configurator is no ready, call configure");
        }

    }

    @SuppressWarnings("unchecked")
    public <T> T getConfiguration(Object key){
        chechConfigurator();
        final Object value = LATTER_CONFIGS.get(key);
        if(value == null){
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTER_CONFIGS.get(key);
    }

}
