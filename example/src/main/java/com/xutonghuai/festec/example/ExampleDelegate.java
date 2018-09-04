package com.xutonghuai.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.view.View;
import android.widget.Toast;

import com.xutonghuai.latter.R2;
import com.xutonghuai.latter.delegates.LatterDelegate;
import com.xutonghuai.latter.net.RestClient;
import com.xutonghuai.latter.net.callback.IError;
import com.xutonghuai.latter.net.callback.IFailure;
import com.xutonghuai.latter.net.callback.ISuccess;

import butterknife.BindView;

/**
 * Created by xutonghuai on 2018-03-17.
 */

public class ExampleDelegate extends LatterDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("https://www.baidu.com")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }

}
