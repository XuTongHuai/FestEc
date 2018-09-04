package com.xutonghuai.latter.ui.recycle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 *
 * Created by xutonghuai on 2018-04-07.
 */
public class MultipleItemEntity implements MultiItemEntity{

    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object,Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERENCE =
            new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS,ITEM_QUEUE);


    @Override
    public int getItemType() {
        return 0;
    }

}
