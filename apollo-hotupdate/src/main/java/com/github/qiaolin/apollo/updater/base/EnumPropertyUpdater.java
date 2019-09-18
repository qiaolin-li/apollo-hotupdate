package com.github.qiaolin.apollo.updater.base;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.updater.AbstractPropertyUpdater;
import org.springframework.stereotype.Component;

/**
 * @author qiaolin
 */

@Component
public class EnumPropertyUpdater extends AbstractBasePropertyUpdater<Enum> {

    @Override
    public boolean support(Class<?> clazz) {
        return Enum.class.isAssignableFrom(clazz);
    }

    @Override
    protected Enum parseValue0(PropertyInfo propertyInfo, String value) {
        Class<Enum> type = (Class<Enum>) propertyInfo.getField().getType();
        return Enum.valueOf(type, value);
    }
}
