package com.xutonghuai.latter.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by xutonghuai on 2018-04-02.
 */

public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean tabBean,BottomItemDelegate itemDelegate){
        ITEMS.put(tabBean,itemDelegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> buid(){
        return ITEMS;
    }

}
