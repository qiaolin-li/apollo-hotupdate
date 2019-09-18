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
public class DoublePropertyUpdater extends AbstractBasePropertyUpdater<Double> {

    @Override
    public boolean support(Class<?> clazz) {
        return Double.class.isAssignableFrom(clazz) || double.class.isAssignableFrom(clazz);
    }

    @Override
    protected Double parseValue0(PropertyInfo propertyInfo, String value) {
        return Double.valueOf(value);
    }
}
