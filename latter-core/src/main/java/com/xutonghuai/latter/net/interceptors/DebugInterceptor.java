package com.xutonghuai.latter.net.interceptors;

import android.support.annotation.RawRes;

import com.xutonghuai.latter.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by xutonghuai on 2018-03-20.
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        DEBUG_URL = debugUrl;
        DEBUG_RAW_ID = rawId;
    }

    private Response getResponse(Chain chain,String json){

        return new Response.Builder()
                //成功
                .code(200)
                //内容为json
                .addHeader("Content-Type","application/json")
                //请求体返回的格式为json形式
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("ok")
                .request(chain.request())
                //请求协议
                .protocol(Protocol.HTTP_1_1)
                .build();

    }

    private Response DefaulResponse(Chain chain,@RawRes int rawId){

        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain,json);

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        //如果存在debug的url，那返回debug_raw_id
        //如果没有，推进返回request
        final String url = chain.request().url().toString();
        if(url.contains(DEBUG_URL)){
            return DefaulResponse(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
