package com.xutonghuai.festec.example.generators;

import com.xutonghuai.latter.wechat.templates.WXPayEntryTemplate;
import com.xutonghuai.latter_annotations.annotations.PayEntryGenerator;

/**
 * Created by xutonghuai on 2018-03-24.
 */
@PayEntryGenerator(
        packageName = "com.xutonghuai.festec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
