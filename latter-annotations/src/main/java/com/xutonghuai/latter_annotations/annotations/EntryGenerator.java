package com.xutonghuai.latter_annotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ElementType.TYPE  告诉编译器这个注解是用在类上面的
 * RetentionPolicy.SOURCE 处理注解的时候在源码阶段，编译后就不再使用
 * Created by xutonghuai on 2018-03-24.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {

    String packageName() ;

    Class<?> entryTemplate();

}
