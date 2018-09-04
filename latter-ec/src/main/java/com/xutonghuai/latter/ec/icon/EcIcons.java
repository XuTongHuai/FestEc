package com.xutonghuai.latter.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * 自定义icon枚举
 * Created by xutonghuai on 2018-03-16.
 */
public enum  EcIcons implements Icon {

    icon_scan('\ue657'),
    icon_ali_pay('\ue602'),
    icon_weixin('\ue64f');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }

}
