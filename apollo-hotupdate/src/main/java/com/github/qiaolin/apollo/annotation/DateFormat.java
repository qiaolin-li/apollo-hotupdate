package com.github.qiaolin.apollo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date 类型格式化注解
 * @author qiaolin
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {

    String value() default "yyyy/MM/dd HH:mm:ss";

}
