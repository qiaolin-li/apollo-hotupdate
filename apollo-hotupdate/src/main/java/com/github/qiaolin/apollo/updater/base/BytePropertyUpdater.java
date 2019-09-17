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
public class BytePropertyUpdater extends AbstractPropertyUpdater<Byte> {

    @Override
    public boolean support(Class<?> clazz) {
        return Byte.class.isAssignableFrom(clazz) || byte.class.isAssignableFrom(clazz);
    }

    @Override
    protected Byte parseValue(PropertyInfo propertyInfo, ConfigChange change) {
        return Byte.valueOf(change.getNewValue().trim());
    }
}
