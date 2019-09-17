package com.github.qiaolin.apollo.resolver;

import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.validator.HotPropertyValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link Value} 注解热更新属性解析器
 * @author qiaolin
 */

@Slf4j
public class ValueAnnotationResolver implements PropertyResolver {

    private final String PREFIX = "${";
    private final String SUFFIX = "}";

    @Autowired
    private HotPropertyValidator hotPropertyValidator;

    @Override
    public List<PropertyInfo> resolver(Object bean) {
        List<PropertyInfo> propertyInfos = new ArrayList<>();
        Field[] declaredFields = bean.getClass().getDeclaredFields();

        for (Field declaredField : declaredFields) {
            Value annotation = declaredField.getAnnotation(Value.class);
            if(annotation == null || !validate(annotation.value())){
                continue;
            }
            String key = buildKey(annotation.value());
            if(!hotPropertyValidator.validate(key)){
                continue;
            }
            propertyInfos.add(new PropertyInfo(key, bean, declaredField, buildDefaultValue((annotation.value()))));
        }

        return propertyInfos;
    }

    private String buildKey(String value){
        value = value.substring(PREFIX.length(), value.length() - SUFFIX.length());
        String[] split = value.split(":");
        return split[0];
    }

    private String buildDefaultValue(String value){
        value = value.substring(PREFIX.length(), value.length() - SUFFIX.length());
        String[] split = value.split(":");
        return split.length > 1 ? split[1] : "";
    }

    /**
     *  验证Value注解使用的正确性
     * @param value
     * @return
     */
    private boolean validate(String value){
        if(StringUtils.isEmpty(value)){
            return false;
        }
        if(!value.startsWith(PREFIX) || !value.endsWith(SUFFIX)){
            return false;
        }
        return true;
    }

}

