package com.xutonghuai.latter.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.xutonghuai.latter.app.Latter;

/**
 * Created by xutonghuai on 2018-03-20.
 */

public final class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Latter.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latter.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
