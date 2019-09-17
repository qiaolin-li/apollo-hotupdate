package com.github.qiaolin.apollo.updater.other;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.updater.AbstractPropertyUpdater;
import org.springframework.stereotype.Component;

/**
 * @author qiaolin
 */

@Component
public class EnumPropertyUpdater extends AbstractPropertyUpdater {

    @Override
    public boolean support(Class<?> clazz) {
        return Enum.class.isAssignableFrom(clazz);
    }

    @Override
    protected Object parseValue(PropertyInfo propertyInfo, ConfigChange change) {
        Class<Enum> type = (Class<Enum>) propertyInfo.getField().getType();
        return Enum.valueOf(type, change.getNewValue());
    }
}
