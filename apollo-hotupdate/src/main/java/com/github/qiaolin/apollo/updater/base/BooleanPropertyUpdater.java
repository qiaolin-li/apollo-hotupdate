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
public class BooleanPropertyUpdater extends AbstractPropertyUpdater<Boolean> {

    @Override
    public boolean support(Class<?> clazz) {
        return Boolean.class.isAssignableFrom(clazz) || boolean.class.isAssignableFrom(clazz);
    }

    @Override
    protected Boolean parseValue(PropertyInfo propertyInfo, ConfigChange change) {
        return Boolean.valueOf(change.getNewValue().trim()) ;
    }
}
