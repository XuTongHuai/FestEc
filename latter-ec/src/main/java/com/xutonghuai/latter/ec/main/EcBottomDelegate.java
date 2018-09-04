package com.xutonghuai.latter.ec.main;

import android.graphics.Color;

import com.xutonghuai.latter.delegates.bottom.BaseBottomDelegate;
import com.xutonghuai.latter.delegates.bottom.BottomItemDelegate;
import com.xutonghuai.latter.delegates.bottom.BottomTabBean;
import com.xutonghuai.latter.delegates.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * Created by xutonghuai on 2018-04-03.
 */

public class EcBottomDelegate extends BaseBottomDelegate {


    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {

        LinkedHashMap<BottomTabBean,BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new SortDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new SortDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new SortDelegate());

        return builder.addItems(items).buid();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#FFFF8800");
    }
}
