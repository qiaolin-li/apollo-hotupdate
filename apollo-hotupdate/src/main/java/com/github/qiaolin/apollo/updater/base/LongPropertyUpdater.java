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
public class LongPropertyUpdater extends AbstractPropertyUpdater<Long> {

    @Override
    public boolean support(Class<?> clazz) {
        return Long.class.isAssignableFrom(clazz) ||  long.class.isAssignableFrom(clazz);
    }

    @Override
    protected Long parseValue(PropertyInfo propertyInfo, ConfigChange change) {
        return Long.valueOf(change.getNewValue().trim());
    }
}
