package com.xutonghuai.latter.ec.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.iconify.widget.IconTextView;
import com.xutonghuai.latter.delegates.bottom.BottomItemDelegate;
import com.xutonghuai.latter.ec.R;
import com.xutonghuai.latter.ec.R2;
import com.xutonghuai.latter.ui.refresh.RefreshHandle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xutonghuai on 2018-04-03.
 */

public class IndexDelegate extends BottomItemDelegate {


    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mSwipeRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIndexScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;
    @BindView(R2.id.icon_index_message)
    IconTextView mIndexMessage = null;

    private RefreshHandle mRefreshHandle = null;

    private void initRefreshLayout(){
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light
        );
        //第一个参数：true说明下拉球从小变大
        //启示高度120，下降到300
        mSwipeRefreshLayout.setProgressViewOffset(true,120,300);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //初始化handle
        mRefreshHandle = new RefreshHandle(mSwipeRefreshLayout);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        initRefreshLayout();
        mRefreshHandle.firstPage("index.php");

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }




}
