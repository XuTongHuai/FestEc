package com.xutonghuai.latter.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xutonghuai.latter.app.AccountManager;
import com.xutonghuai.latter.ec.database.DatabaseManager;
import com.xutonghuai.latter.ec.database.UserProfile;

/**
 * Created by xutonghuai on 2018-03-23.
 */

public class SignHandler {

    public static void onSignIn(String response,ISignListener signListener) {

        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();

    }

    /**
     * 登录成功
     * @param response
     * @param signListener
     */
    public static void onSignUp(String response,ISignListener signListener) {

        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        //保存登录状态
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();

    }

}