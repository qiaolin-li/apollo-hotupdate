package com.github.qiaolin.apollo.resolver;

import com.github.qiaolin.apollo.annotation.DefaultValue;
import com.github.qiaolin.apollo.properties.HotUpdateProperties;
import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.validator.HotPropertyValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 热更新属性解析器
 * <p>
 * 这个类主要是去解析 spring的 {@link ConfigurationProperties} 注解
 * 将使用这个注解的类的所有属性解析出来
 * <p>
 * 解析包含了属性所对应的键值，例如我们有如下一个类
 * <pre>
 * @ConfigurationProperties("com.github.qiaolin.core")
 * public class CoreProperties{
 *      private int pageSize;
 *
 * }
 * </pre>
 * pageSize的解析出来的键值就为 com.github.qiaolin.core.pageSize
 *
 * 支持层级属性，但是需要保证你的层级属性不为空，也就是你必须有设值，否则回被丢弃，不在监控范围内
 *
 * @author qiaolin
 */

@Slf4j
public class ConfigurationPropertiesResolver implements PropertyResolver {
    /**
     *  基本类型支持
     */
    private static final Set<Class<?>> BASE_TYPE = new HashSet<>();;

    @Autowired
    private HotUpdateProperties hotUpdateProperties;

    @Autowired
    private HotPropertyValidator hotPropertyValidator;

    static {
        BASE_TYPE.addAll(Arrays.asList(Byte.class, byte.class, Byte[].class, byte.class));
        BASE_TYPE.addAll(Arrays.asList(Character.class, char.class, Character[].class, char.class));
        BASE_TYPE.addAll(Arrays.asList(Short.class, short.class, Short[].class, short.class));
        BASE_TYPE.addAll(Arrays.asList(Integer.class, int.class, Integer[].class, int.class));
        BASE_TYPE.addAll(Arrays.asList(Float.class, float.class, Float[].class, float.class));
        BASE_TYPE.addAll(Arrays.asList(Long.class, long.class, Long[].class, long.class));
        BASE_TYPE.addAll(Arrays.asList(Double.class, double.class, Double[].class, double.class));
        BASE_TYPE.addAll(Arrays.asList(Boolean.class, boolean.class, Boolean[].class, boolean.class));
        BASE_TYPE.addAll(Arrays.asList(String.class, String[].class));
        BASE_TYPE.addAll(Arrays.asList(Enum.class, Enum[].class));
        BASE_TYPE.addAll(Arrays.asList(Date.class, Date[].class));
    }

    @Override
    public List<PropertyInfo> resolver(Object bean) {
        Class<?> cls = bean.getClass();
        List<PropertyInfo> propertyInfos = new ArrayList<>();
        ConfigurationProperties annotation = cls.getAnnotation(ConfigurationProperties.class);
        if(annotation == null){
            return propertyInfos;
        }

        return recursion(bean, cls, getAnnotationPrefixKey(annotation), 0);
    }

    private List<PropertyInfo> recursion(Object bean, Class<?> clz, String prefixKey, int level)  {
        List<PropertyInfo> result = new ArrayList<>();
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            String key = buildKey(prefixKey,  name);
            if(!hotPropertyValidator.validate(key)){
                continue;
            }
            if(BASE_TYPE.contains(declaredField.getType())){
                DefaultValue annotation = declaredField.getAnnotation(DefaultValue.class);
                String defaultValue = (annotation == null) ? "" : annotation.value();
                result.add(new PropertyInfo(key, bean, declaredField, defaultValue));
            }else {
                // 层级太多了，可能会栈溢出
                int newLevel = level + 1;
                if(newLevel < hotUpdateProperties.getRecursionLevel()){
                    try {
                        declaredField.setAccessible(true);
                        Object o = declaredField.get(bean);
                        if(o == null){
                            continue;
                        }
                        result.addAll(recursion(o, declaredField.getType(), key, newLevel));
                    } catch (IllegalAccessException e) {
                        log.warn("获取bean属性出错 ", e);
                    }
                }
            }
        }
        return result;
    }


    private String getAnnotationPrefixKey(ConfigurationProperties annotation){
        return StringUtils.isEmpty(annotation.value()) ? annotation.prefix() : annotation.value();
    }

    private String buildKey(String prefix, String fieldName){
        return prefix + "." + fieldName;
    }


}
