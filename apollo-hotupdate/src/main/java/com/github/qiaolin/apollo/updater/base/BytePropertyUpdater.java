package com.github.qiaolin.apollo.updater.base;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.updater.AbstractPropertyUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author qiaolin
 */

@Slf4j
@Component
public class BytePropertyUpdater extends AbstractBasePropertyUpdater<Byte> {

    @Override
    public boolean support(Class<?> clazz) {
        return Byte.class.isAssignableFrom(clazz) || byte.class.isAssignableFrom(clazz);
    }

    @Override
    protected Byte parseValue0(PropertyInfo propertyInfo, String value) {
        return Byte.valueOf(value);
    }
}
