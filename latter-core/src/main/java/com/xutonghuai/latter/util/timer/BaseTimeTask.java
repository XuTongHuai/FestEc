package com.xutonghuai.latter.util.timer;

import java.util.TimerTask;

/**
 * Created by xutonghuai on 2018-03-21.
 */
public class BaseTimeTask extends TimerTask {

    private ITimerListener mITimerListener;

    public BaseTimeTask(ITimerListener iTimerListener) {
        this.mITimerListener = iTimerListener;
    }

    @Override
    public void run() {

        if(mITimerListener!=null){
            mITimerListener.onTimer();
        }

    }
}
