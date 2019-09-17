package com.github.qiaolin.apollo.updater.array;

import com.github.qiaolin.apollo.support.PropertyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Boolean数组类型更新器
 * @author qiaolin
 */
@Slf4j
@Component
public class BooleanArrayPropertyUpdater extends AbstractArrayPropertyUpdater<Boolean> {

    @Override
    public boolean support(Class<?> clazz) {
        return Boolean[].class.isAssignableFrom(clazz) || boolean[].class.isAssignableFrom(clazz);
    }


    @Override
    protected Boolean formatItemValue(String value, PropertyInfo propertyInfo) {
        return Boolean.valueOf(value);
    }

}
