package com.github.qiaolin.apollo.updater.array;

import com.github.qiaolin.apollo.support.PropertyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Short数组类型更新器
 * @author qiaolin
 */
@Slf4j
@Component
public class ShortArrayPropertyUpdater extends AbstractArrayPropertyUpdater<Short> {

    @Override
    public boolean support(Class<?> clazz) {
        return Short[].class.isAssignableFrom(clazz) || short[].class.isAssignableFrom(clazz);
    }


    @Override
    protected Short formatItemValue(String value, PropertyInfo propertyInfo) {
        return Short.valueOf(value);
    }
}
