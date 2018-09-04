package com.xutonghuai.festec.example.generators;

import com.xutonghuai.latter.wechat.templates.AppRegisterTemplate;
import com.xutonghuai.latter_annotations.annotations.AppRegisterGenerator;

@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.xutonghuai.festec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
