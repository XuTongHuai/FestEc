package com.xutonghuai.latter.net;

import com.xutonghuai.latter.app.ConfigKeys;
import com.xutonghuai.latter.app.Latter;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 通过RestCreator.getRestService()获取RestService的实例
 * Created by xutonghuai on 2018-03-17.
 */
public class RestCreator {

    private static final class ParamsHolder{
        private static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * RetrofitHolder.RETROFIT_CLIENT 创建Retrofit的实例
     */
    private static final class RetrofitHolder{
        private static final String BASE_URL =
                (String) Latter.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * okthttpHolder
     * OKHttpHolder.OK_HTTP_CLIENT 创建okhttpClient的实力
     */
    private static final class OKHttpHolder{
        private static final int TIME_OUT = 60;
        //获取拦截器arraylist
        private static final ArrayList<Interceptor> INTERCEPTORS = Latter.getConfiguration(ConfigKeys.INTERCEPTOR);
        //okhttp建造者模式
        private static final OkHttpClient.Builder  BUILDER = new OkHttpClient.Builder();
        //添加拦截器
        private static OkHttpClient.Builder addInterceptor(){
            if(INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor: INTERCEPTORS ) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * RestserviceHoder
     * 通过RestServiceHolder.REST_SERVICE.create 创建RestServiced的实例
     *
     */
    private static final class RestServiceHolder{

        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

    }

}
