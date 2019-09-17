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
public class FloatPropertyUpdater extends AbstractPropertyUpdater<Float> {

    @Override
    public boolean support(Class<?> clazz) {
        return Float.class.isAssignableFrom(clazz) || float.class.isAssignableFrom(clazz);
    }

    @Override
    protected Float parseValue(PropertyInfo propertyInfo, ConfigChange change) {
        return Float.valueOf(change.getNewValue().trim());
    }
}
