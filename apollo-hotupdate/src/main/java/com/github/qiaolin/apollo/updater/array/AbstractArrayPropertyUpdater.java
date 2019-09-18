package com.github.qiaolin.apollo.updater.array;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.github.qiaolin.apollo.exception.UpdatePropertyException;
import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.updater.AbstractPropertyUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


/**
 * 数组抽象属性更新器
 * @author qiaolin
 */
@Slf4j
public abstract class AbstractArrayPropertyUpdater<T> extends AbstractPropertyUpdater<T[]> {


    @Override
    protected T[] parseValue(PropertyInfo propertyInfo, ConfigChange change){
        try {
            String[] arrayProperty = getConfig().getArrayProperty(propertyInfo.getKey(), ",", new String[]{});
            return parseValue0(propertyInfo, arrayProperty);
        } catch (Exception e) {
            if(!StringUtils.isEmpty(propertyInfo.getDefaultValue())){
                log.warn("设置的值不正确，使用默认值！ propertyKey:{}, oldValue:{}, newValue:{}, defaultValue:{}",
                        propertyInfo.getKey(), change.getOldValue(), change.getNewValue(), propertyInfo.getDefaultValue(), e);
                String[] strings = defaultValueToArray(propertyInfo.getDefaultValue());
                return parseValue0(propertyInfo, strings);
            }
            throw e;
        }
    }

    protected T[] parseValue0(PropertyInfo propertyInfo, String[] arrayProperty) {
        // 如果不能知道准确的类型，如果返回一个Object[] 数组出去，但你的属性实际上是 String[] ，
        // 会出现 参数异常
        T[] array = (T[]) Array.newInstance(getActualTypeArgument(), arrayProperty.length);

        IntStream.range(0, arrayProperty.length).forEach(i ->{
            array[i] = formatItemValue(arrayProperty[i].trim(), propertyInfo);

        });

        return array;
    }

    /**
     *  格式化数组中每一个元素
     * @param value 字符串类型的值
     * @param propertyInfo 属性信息
     * @return 对应类型的元素
     */
    protected abstract T formatItemValue(String value, PropertyInfo propertyInfo);


    /**
     *  将默认值转换成string数组
     * @param str
     * @return
     */
    private String[] defaultValueToArray(String str){
        String[] split = str.split(",");
        List<String> result = new ArrayList<>(split.length);
        for (String s : split) {
            if(s != null && !"".equals(s.trim())){
                result.add(s);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    /**
     * 获取泛型类Class对象，不是泛型类则返回null
     */
    private Class<?> getActualTypeArgument() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                return (Class<?>) actualTypeArguments[0];
            }
        }

        throw new UpdatePropertyException("找不到class的泛型，未知的转换");
    }
}


