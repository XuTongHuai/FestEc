package com.xutonghuai.latter.ec.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xutonghuai.latter.delegates.bottom.BottomItemDelegate;
import com.xutonghuai.latter.ec.R;

/**
 * Created by xutonghuai on 2018-04-03.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
