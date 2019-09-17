package com.github.qiaolin.apollo.updater.array;

import com.github.qiaolin.apollo.support.PropertyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Long数组类型更新器
 * @author qiaolin
 */
@Slf4j
@Component
public class LongArrayPropertyUpdater extends AbstractArrayPropertyUpdater<Long> {

    @Override
    public boolean support(Class<?> clazz) {
        return Long[].class.isAssignableFrom(clazz) || long[].class.isAssignableFrom(clazz);
    }


    @Override
    protected Long formatItemValue(String value, PropertyInfo propertyInfo) {
        return Long.valueOf(value);
    }
}
