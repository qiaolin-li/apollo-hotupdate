package com.github.qiaolin.apollo.updater.array;

import com.github.qiaolin.apollo.support.PropertyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Float数组类型更新器
 * @author qiaolin
 */
@Slf4j
@Component
public class FloatArrayPropertyUpdater extends AbstractArrayPropertyUpdater<Float> {

    @Override
    public boolean support(Class<?> clazz) {
        return Float[].class.isAssignableFrom(clazz) || float[].class.isAssignableFrom(clazz);
    }


    @Override
    protected Float formatItemValue(String value, PropertyInfo propertyInfo) {
        return Float.valueOf(value);
    }
}
