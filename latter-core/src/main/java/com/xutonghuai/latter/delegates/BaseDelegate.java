package com.xutonghuai.latter.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xutonghuai.latter.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by xutonghuai on 2018-03-17.
 */

public abstract class BaseDelegate extends SwipeBackFragment {

    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = null;

        if(setLayout() instanceof Integer){
            //如果setLayout()是id
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }else if(setLayout() instanceof View){
            //如果setLayout()是view
            rootView = (View) setLayout();
        }else{
            //类转换异常
            throw new ClassCastException("type of setLayout() must be int or View!");
        }

        if(rootView!=null){
            //绑定当前fragment类，在rootview中查找资源
            mUnbinder = ButterKnife.bind(this,rootView);
            onBindView(savedInstanceState,rootView);
        }

        //侧滑上一级
        return attachToSwipeBack(rootView);

    }

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
    }
}
