package com.xutonghuai.festec.example.generators;

/**
 * Created by xutonghuai on 2018-03-24.
 */

import com.xutonghuai.latter.wechat.templates.WXEntryTemplate;
import com.xutonghuai.latter_annotations.annotations.EntryGenerator;

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.xutonghuai.festec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
