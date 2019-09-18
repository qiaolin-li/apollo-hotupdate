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
public class BooleanPropertyUpdater extends AbstractBasePropertyUpdater<Boolean> {

    @Override
    public boolean support(Class<?> clazz) {
        return Boolean.class.isAssignableFrom(clazz) || boolean.class.isAssignableFrom(clazz);
    }

    @Override
    protected Boolean parseValue0(PropertyInfo propertyInfo, String value) {
        return Boolean.valueOf(value) ;
    }
}
