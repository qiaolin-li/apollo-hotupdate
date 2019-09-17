package com.github.qiaolin.apollo.annotation;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 默认值注解
 * 用于支持给 {@link ConfigurationProperties} 设置默认值
 * @author qiaolin
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultValue {

    String value();
}
