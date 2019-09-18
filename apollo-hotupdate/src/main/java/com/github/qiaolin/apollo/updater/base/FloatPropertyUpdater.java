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
public class FloatPropertyUpdater extends AbstractBasePropertyUpdater<Float> {

    @Override
    public boolean support(Class<?> clazz) {
        return Float.class.isAssignableFrom(clazz) || float.class.isAssignableFrom(clazz);
    }

    @Override
    protected Float parseValue0(PropertyInfo propertyInfo, String value) {
        return Float.valueOf(value);
    }
}
