package com.xutonghuai.latter.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * loader创建器
 * 缓存的方式来创建loader，不需要每次都反射去创建一个loader
 * Created by xutonghuai on 2018-03-20.
 */
public final class LoaderCreator {

    private static final WeakHashMap<String,Indicator> LOADING_MAP = new WeakHashMap<>();

    //返回AVLoadingIndicatorView实例
    static AVLoadingIndicatorView create(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if(LOADING_MAP.get(type)==null){
            //通过type命称初始化获取默认indicator实例
            final Indicator indicator = getIndicator(type);
            //加入weakHashMap进行统一的管理
            LOADING_MAP.put(type,indicator);
        }
        //设置当前type对应的指示器
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return  avLoadingIndicatorView;
    }

    /**
     * 反射，通过类名去实例化当前Indicator的实例
     * @param name
     * @return
     */
    private static Indicator getIndicator(String name){
        if(name == null || name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if(!name.contains(".")){
            //包名
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            //拼接Indicator的包名.类名
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        //添加类名
        drawableClassName.append(name);
        try {
            //反射获取当前类名的类
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            //实例化当前类
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            //异常返回null
            return null;
        }

    }

}
