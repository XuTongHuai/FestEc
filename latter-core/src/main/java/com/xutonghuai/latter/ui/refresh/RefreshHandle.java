package com.xutonghuai.latter.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.xutonghuai.latter.app.Latter;
import com.xutonghuai.latter.net.RestClient;
import com.xutonghuai.latter.net.callback.ISuccess;

/**
 * 刷新助手
 * Created by xutonghuai on 2018-04-07.
 */
public class RefreshHandle implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandle(SwipeRefreshLayout refresh_layout) {
        REFRESH_LAYOUT = refresh_layout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public void refresh(){
        //开始准备刷新
//        REFRESH_LAYOUT.setRefreshing(true);
        Latter.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }

    public void firstPage(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latter.getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }


    @Override
    public void onRefresh() {
        refresh();
    }

}
