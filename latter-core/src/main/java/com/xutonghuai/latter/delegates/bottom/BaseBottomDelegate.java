package com.xutonghuai.latter.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.xutonghuai.latter.R;
import com.xutonghuai.latter.R2;
import com.xutonghuai.latter.delegates.LatterDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by xutonghuai on 2018-04-02.
 */

public abstract class BaseBottomDelegate extends LatterDelegate implements View.OnClickListener{

    /** 存储子bean **/
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    /** 存储子delegate **/
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    /** 打卡app看到的bottom **/
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;
    /** 传入ItemBuilder 通过ItemBuilder添加k-v **/
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    /** 设置首次显示的delegate **/
    public abstract int setIndexDelegate();

    /** 设置点击的颜色
     *  @colorInt 表示返回的是颜色的int
     * **/
    @ColorInt
    public abstract int setClickedColor();

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if(setClickedColor()!=0){
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        //传入builder，设置items
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean,BottomItemDelegate> item: items.entrySet()) {
            final BottomTabBean bean = item.getKey();
            final BottomItemDelegate delegate = item.getValue();
            TAB_BEANS.add(bean);
            ITEM_DELEGATES.add(delegate);
        }

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //TODO 对icon和text进行初始化，设置点击事件等
        final int size = ITEMS.size();
        for (int i=0 ;i<size;i++){
            //将mBottomBar作为bottom_item_icon_text_layout的根节点
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout,mBottomBar);
            final RelativeLayout item = (RelativeLayout)mBottomBar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView iconTextView = (IconTextView) item.getChildAt(0);
            final AppCompatTextView appCompatTextView = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bottomTabBean = TAB_BEANS.get(i);
            //初始化数据
            iconTextView.setText(bottomTabBean.getIcon());
            appCompatTextView.setText(bottomTabBean.getTitle());
            if(i == mIndexDelegate){
                iconTextView.setTextColor(mClickedColor);
                appCompatTextView.setTextColor(mClickedColor);
            }
        }

        //转换为ISupportFragment数组
        ISupportFragment[] iSupportFragments = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        //同级添加多个fragment给delegate_container
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container,mIndexDelegate,iSupportFragments);

    }

    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i=0 ;i<count; i++){
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView iconTextView = (IconTextView) item.getChildAt(0);
            iconTextView.setTextColor(Color.GRAY);
            final AppCompatTextView appCompatTextView = (AppCompatTextView) item.getChildAt(1);
            appCompatTextView.setTextColor(Color.GRAY);
        }

    }

    @Override
    public void onClick(View v) {

        //获取当前点击的bottom的tag
        int tag = (int) v.getTag();
        resetColor();
        RelativeLayout item = (RelativeLayout) v;
        final IconTextView iconTextView = (IconTextView) item.getChildAt(0);
        iconTextView.setTextColor(mClickedColor);
        final AppCompatTextView appCompatTextView = (AppCompatTextView) item.getChildAt(1);
        appCompatTextView.setTextColor(mClickedColor);
        //显示点击后讲显示的页面，隐藏当先的页面
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag),ITEM_DELEGATES.get(mCurrentDelegate));
        //注意顺序，设置当前的fragment页面tag
        mCurrentDelegate = tag;

    }
}
