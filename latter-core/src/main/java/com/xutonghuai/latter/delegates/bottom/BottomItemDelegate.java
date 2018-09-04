package com.xutonghuai.latter.delegates.bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.xutonghuai.latter.R;
import com.xutonghuai.latter.app.Latter;
import com.xutonghuai.latter.delegates.LatterDelegate;

/**
 * Created by xutonghuai on 2018-04-02.
 */

public abstract class BottomItemDelegate extends LatterDelegate {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latter.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
