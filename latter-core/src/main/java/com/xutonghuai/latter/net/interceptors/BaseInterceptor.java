package com.xutonghuai.latter.net.interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xutonghuai on 2018-03-20.
 */

public abstract class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }

    /**
     * 获取全部的有序的参数对
     * @param chain
     * @return
     */
    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){
        final HttpUrl httpUrl = chain.request().url();
        //请求参数的个数
        int size = httpUrl.querySize();
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();
        for (int i=0;i<size;i++){
            params.put(httpUrl.queryParameterName(i),httpUrl.queryParameterValue(i));
        }
        return params;
    }

    /**
     * 通过key获取params的值
     * @param chain
     * @param key
     * @return
     */
    private String getUrlParameters(Chain chain,String key){
        Request request = chain.request();
        return request.url().queryParameter(key);
    }

    /**
     * 获取body类型的参数值
     * @param chain
     * @return
     */
    protected LinkedHashMap<String,String> getBodyParameters(Chain chain){
        final FormBody body = (FormBody) chain.request().body();
        int size = body.size();
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();
        for (int i=0;i<size;i++){
            params.put(body.name(i),body.value(i));
        }
        return params;
    }

    private String getBodyParameters(Chain chain,String key){
        return getBodyParameters(chain).get(key);
    }

}
