package com.xutonghuai.latter.net.callback;

import android.os.Handler;

import com.xutonghuai.latter.ui.loader.LatteLoader;
import com.xutonghuai.latter.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xutonghuai on 2018-03-19.
 */

public class RequestCallbacks implements Callback<String>{

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {

        if(response.isSuccessful()){//请求成功
            if(call.isExecuted()){//回调已经执行
                if(SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if(ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }
        }

        stopLoading();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

        if(FAILURE!=null){
            FAILURE.onFailure();
        }

        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }

        stopLoading();

    }

    private void stopLoading() {
        if(LOADER_STYLE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },2000);
        }
    }

}
