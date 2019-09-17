package com.github.qiaolin.apollo.updater.array;

import com.github.qiaolin.apollo.support.PropertyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Int数组类型更新器
 * @author qiaolin
 */
@Slf4j
@Component
public class IntegerArrayPropertyUpdater extends AbstractArrayPropertyUpdater<Integer> {

    @Override
    public boolean support(Class<?> clazz) {
        return Integer[].class.isAssignableFrom(clazz) || int[].class.isAssignableFrom(clazz);
    }


    @Override
    protected Integer formatItemValue(String value, PropertyInfo propertyInfo) {
        return Integer.valueOf(value);
    }
}
