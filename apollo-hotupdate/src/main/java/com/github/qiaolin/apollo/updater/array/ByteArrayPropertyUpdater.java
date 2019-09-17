package com.github.qiaolin.apollo.updater.array;

import com.github.qiaolin.apollo.support.PropertyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Byte数组类型更新器
 * @author qiaolin
 */
@Slf4j
@Component
public class ByteArrayPropertyUpdater extends AbstractArrayPropertyUpdater<Byte> {

    @Override
    public boolean support(Class<?> clazz) {
        return Byte[].class.isAssignableFrom(clazz) || byte[].class.isAssignableFrom(clazz);
    }


    @Override
    protected Byte formatItemValue(String value, PropertyInfo propertyInfo) {
        return Byte.valueOf(value);
    }
}
