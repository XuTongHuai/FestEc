package com.xutonghuai.latter.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by xutonghuai on 2018-03-14.
 */

public final class Latter {

    public static Configurator init(Context context){

        Configurator.getInstance()
                .getLatterConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurators(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurators().getConfiguration(key);
    }

    public static Context getApplicationContext(){
        return (Context) getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }


}
