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
public class ShortPropertyUpdater extends AbstractPropertyUpdater<Short> {

    @Override
    public boolean support(Class<?> clazz) {
        return Short.class.isAssignableFrom(clazz) || short.class.isAssignableFrom(clazz);
    }

    @Override
    protected Short parseValue(PropertyInfo propertyInfo, ConfigChange change) {
        return Short.valueOf(change.getNewValue().trim());
    }
}
