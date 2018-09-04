package com.xutonghuai.latter.app;

/**
 * 用户登录状态检查回掉
 * Created by xutonghuai on 2018-03-23.
 */
public interface IUserChecker {

    void onSignIn();

    void onNoSignIn();

}
