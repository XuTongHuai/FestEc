package com.xutonghuai.latter.ui.launcher;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by xutonghuai on 2018-03-22.
 */

public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView appCompatImageView;

    @Override
    public View createView(Context context) {
        appCompatImageView = new AppCompatImageView(context);
        return appCompatImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        appCompatImageView.setBackgroundResource(data);
    }
}
