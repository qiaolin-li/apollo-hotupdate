package com.github.qiaolin.apollo.updater.array;

import com.github.qiaolin.apollo.support.PropertyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * String数组类型更新器
 * @author qiaolin
 */
@Slf4j
@Component
public class StringArrayPropertyUpdater extends AbstractArrayPropertyUpdater<String> {

    @Override
    public boolean support(Class<?> clazz) {
        return String[].class.isAssignableFrom(clazz);
    }


    @Override
    protected String formatItemValue(String value, PropertyInfo propertyInfo) {
        // apollo的所有数组元素类型都是String, 所以这里直接返回就好了
        return value;
    }
}
