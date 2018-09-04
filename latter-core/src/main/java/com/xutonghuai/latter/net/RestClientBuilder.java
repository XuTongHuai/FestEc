package com.xutonghuai.latter.net;

import android.content.Context;

import com.xutonghuai.latter.net.callback.IError;
import com.xutonghuai.latter.net.callback.IFailure;
import com.xutonghuai.latter.net.callback.IRequest;
import com.xutonghuai.latter.net.callback.ISuccess;
import com.xutonghuai.latter.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by xutonghuai on 2018-03-19.
 */

public class RestClientBuilder {

    private String mUrl = null;
    private WeakHashMap<String, Object> mParams = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private File file = null;
    private LoaderStyle mLoaderStyle = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        if (mParams == null) {
            mParams = new WeakHashMap<>();
        }
        this.mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.file = file;
        return this;
    }

    public final RestClientBuilder file(String filePath) {
        this.file = new File(filePath);
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

    private Map<String, Object> checkParams() {
        if (mParams == null) {
            return new WeakHashMap<>();
        }
        return mParams;
    }

    public final RestClient build() {
        return new RestClient(mUrl, mParams, mIRequest, mISuccess, mIFailure, mIError, mBody,mContext,file,mLoaderStyle,mDownloadDir,mExtension,mName );
    }


}
