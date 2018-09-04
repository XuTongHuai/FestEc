package com.xutonghuai.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.xutonghuai.latter.activities.ProxyActivity;
import com.xutonghuai.latter.delegates.LatterDelegate;
import com.xutonghuai.latter.ec.launcher.ILauncherListener;
import com.xutonghuai.latter.ec.launcher.LauncherDelegate;
import com.xutonghuai.latter.ec.launcher.OnLauncherFinishTag;
import com.xutonghuai.latter.ec.main.EcBottomDelegate;
import com.xutonghuai.latter.ec.sign.ISignListener;
import com.xutonghuai.latter.ec.sign.SignInDelegate;
import com.xutonghuai.latter.ec.sign.SignUpDelegate;


public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    public LatterDelegate setRootDelegate() {
//        return new ExampleDelegate();
        return new LauncherDelegate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                Toast.makeText(this,"已登录",Toast.LENGTH_LONG).show();
//                startWithPop(new EcBottomDelegate());
                break;
            case NO_SIGNED:
                Toast.makeText(this,"未登录",Toast.LENGTH_LONG).show();
//                startWithPop(new SignInDelegate());
                startWithPop(new EcBottomDelegate());
                break;
            default:
                break;
        }
    }
}
