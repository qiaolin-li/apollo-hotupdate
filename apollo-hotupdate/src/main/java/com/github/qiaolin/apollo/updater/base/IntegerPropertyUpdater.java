package com.github.qiaolin.apollo.updater.base;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.updater.AbstractPropertyUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author qiaolin
 */

@Slf4j
@Component
public class IntegerPropertyUpdater extends AbstractPropertyUpdater<Integer> {

    @Override
    public boolean support(Class<?> clazz) {
        return Integer.class.isAssignableFrom(clazz) || int.class.isAssignableFrom(clazz);
    }

    @Override
    protected Integer parseValue(PropertyInfo propertyInfo, ConfigChange change) {
        try {
            return Integer.valueOf(change.getNewValue().trim());
        } catch (NumberFormatException e) {
            // 再抢救一下
            return Integer.valueOf(propertyInfo.getDefaultValue());
        }
    }
}
