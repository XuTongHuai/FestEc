package com.xutonghuai.latter.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by xutonghuai on 2018-03-22.
 */

public class FontWXModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "weixin.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
