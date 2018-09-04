package com.xutonghuai.latter.app;

import android.app.Fragment;

import com.xutonghuai.latter.util.storage.LattePreference;

import java.security.PublicKey;

/**
 * Created by xutonghuai on 2018-03-23.
 */

public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    /**
     * 登录成功后保存用户登录状态
     * @param state
     */
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    /**
     * 获取用户登录状态
     */
    public static boolean isSignState(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    /**
     * 检察用户登录状态
     * @param userChecker
     */
    public static void checkAccount(IUserChecker userChecker){
        if(isSignState()){
            userChecker.onSignIn();
        }else {
            userChecker.onNoSignIn();
        }
    }

}
