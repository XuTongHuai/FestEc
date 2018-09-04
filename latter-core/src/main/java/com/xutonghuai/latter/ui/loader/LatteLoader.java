package com.xutonghuai.latter.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;
import com.xutonghuai.latter.R;
import com.xutonghuai.latter.util.dimen.DimenUtil;

import java.util.ArrayList;

/**
 * Created by xutonghuai on 2018-03-20.
 */

public class LatteLoader {

    /**
     * loader屏占比
     */
    private static final int LOADER_SIZE_SCALE = 8;

    /**
     * 偏移量
     */
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallSpinFadeLoaderIndicator.name();

    public static void showLoading(Context context,Enum<LoaderStyle> styleEnum){
        showLoading(context,styleEnum.name());
    }


    public static void showLoading(Context context,String type){
        //创建自己的dialog
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        //创建avloadingview
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type,context);
        //dialog设置为avloadingview
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();
        if(dialogWindow!=null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth/LOADER_SIZE_SCALE;
            lp.height = deviceHeight/LOADER_SIZE_SCALE;
            lp.height = lp.height+deviceHeight/LOADER_SIZE_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();

    }

    /** 默认loading **/
    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }

    public static void stopLoading(){
        for (AppCompatDialog dialog: LOADERS){
            if(dialog!=null){
                if(dialog.isShowing()){
                    //cancel会执行回调
                    dialog.cancel();
                }
            }
        }
    }

}
