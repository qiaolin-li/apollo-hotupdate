package com.github.qiaolin.apollo.resolver;

import com.github.qiaolin.apollo.annotation.HotUpdateFiled;
import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.validator.HotPropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link} HotUpdateField注解对应的解析器
 * <p>
 * 它与 {@link ConfigurationPropertiesResolver}的区别在于，作用于很小，
 * 它可以注解到任何一个bean的属性，不影响到其他不需要热更新的属性，
 * 也可以说它热更新面积只是自己做标注的属性，
 * 而 {@link ConfigurationPropertiesResolver} 热更新的面积是一个整个配置类。
 * @author qiaolin
 */
public class HotUpdateFieldResolver implements PropertyResolver{

    @Autowired
    private HotPropertyValidator hotPropertyValidator;

    @Override
    public List<PropertyInfo> resolver(Object bean) {
        Class<?> cls = bean.getClass();
        Field[] declaredFields = cls.getDeclaredFields();
        List<PropertyInfo> propertyInfos = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            HotUpdateFiled annotation = declaredField.getAnnotation(HotUpdateFiled.class);
            if (annotation == null){
                continue;
            }
            String key = null;
            if(!StringUtils.isEmpty(annotation.prefix())){
                key = annotation.prefix() + "." + annotation.value();
            }else{
                key = annotation.value();
            }
            if(!hotPropertyValidator.validate(key)){
                continue;
            }
            propertyInfos.add(new PropertyInfo(key, bean, declaredField, annotation.defaultValue()));
        }
        return propertyInfos;
    }

}
