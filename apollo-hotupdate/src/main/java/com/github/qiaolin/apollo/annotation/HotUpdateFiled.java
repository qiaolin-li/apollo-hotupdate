package com.github.qiaolin.apollo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 热更新属性注解
 * @author qiaolin
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HotUpdateFiled {

    String prefix() default "";

    String value();

    String defaultValue() default "";
}
