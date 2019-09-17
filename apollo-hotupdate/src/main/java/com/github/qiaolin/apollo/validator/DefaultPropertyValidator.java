package com.github.qiaolin.apollo.validator;

import com.github.qiaolin.apollo.matcher.PropertyKeyMatcher;
import com.github.qiaolin.apollo.properties.HotUpdateProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 默认属性验证器
 * <p> 主要通过 {@link HotUpdateProperties#getUpdateKeys()} 表达式来验证属性是否需要热更新
 * @author qiaolin
 */

public class DefaultPropertyValidator implements HotPropertyValidator {

    @Autowired
    private HotUpdateProperties hotUpdateProperties;

    @Autowired
    private PropertyKeyMatcher propertyKeyMatcher;

    @Override
    public boolean validate(String key) {
        return propertyKeyMatcher.anyMatch(hotUpdateProperties.getUpdateKeys(), key);
    }
}
