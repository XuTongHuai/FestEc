package com.xutonghuai.latter.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.xutonghuai.latter.app.AccountManager;
import com.xutonghuai.latter.app.IUserChecker;
import com.xutonghuai.latter.ec.R;
import com.xutonghuai.latter.delegates.LatterDelegate;
import com.xutonghuai.latter.ec.R2;
import com.xutonghuai.latter.util.storage.LattePreference;
import com.xutonghuai.latter.util.timer.BaseTimeTask;
import com.xutonghuai.latter.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by xutonghuai on 2018-03-21.
 */

public class LauncherDelegate extends LatterDelegate implements ITimerListener {

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView tvLauncherTimer;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if(mTimer!=null){
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        mTimer = new Timer();
        final BaseTimeTask baseTimeTask = new BaseTimeTask(this);
        mTimer.schedule(baseTimeTask,0,1000);

    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll(){
        if(!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            getSupportDelegate().startWithPop(new LauncherScrollDelegate());
        }else{
            //检查用户是否登录了APP
           AccountManager.checkAccount(new IUserChecker() {
               @Override
               public void onSignIn() {
                   if (mILauncherListener != null) {
                       mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                   }
               }

               @Override
               public void onNoSignIn() {
                   if (mILauncherListener != null) {
                       mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NO_SIGNED);
                   }
               }
           });

        }
    }

    @Override
    public void onTimer() {

        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTimer!=null){

                    tvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;

                    if(mCount<0){
                        if(mTimer!=null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }

                }
            }
        });

    }


}
