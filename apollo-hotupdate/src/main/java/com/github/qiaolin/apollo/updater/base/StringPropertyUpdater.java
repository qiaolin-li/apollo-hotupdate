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
public class StringPropertyUpdater extends AbstractPropertyUpdater<String> {

    @Override
    public boolean support(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    protected String parseValue(PropertyInfo propertyInfo, ConfigChange change) {
        return change.getNewValue().trim();
    }
}
