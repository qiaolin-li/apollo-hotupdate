package com.github.qiaolin.apollo.updater.array;

import com.github.qiaolin.apollo.support.PropertyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Double数组类型更新器
 * @author qiaolin
 */
@Slf4j
@Component
public class DoubleArrayPropertyUpdater extends AbstractArrayPropertyUpdater<Double> {

    @Override
    public boolean support(Class<?> clazz) {
        return Double[].class.isAssignableFrom(clazz) || double[].class.isAssignableFrom(clazz);
    }


    @Override
    protected Double formatItemValue(String value, PropertyInfo propertyInfo) {
        return Double.valueOf(value);
    }
}
