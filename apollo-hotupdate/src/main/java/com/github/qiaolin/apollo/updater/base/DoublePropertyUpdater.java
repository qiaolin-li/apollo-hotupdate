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
public class DoublePropertyUpdater extends AbstractPropertyUpdater<Double> {

    @Override
    public boolean support(Class<?> clazz) {
        return Double.class.isAssignableFrom(clazz) || double.class.isAssignableFrom(clazz);
    }

    @Override
    protected Double parseValue(PropertyInfo propertyInfo, ConfigChange change) {
        return Double.valueOf(change.getNewValue().trim());
    }
}
