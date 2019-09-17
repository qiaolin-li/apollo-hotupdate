package com.github.qiaolin.apollo.updater.base;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.github.qiaolin.apollo.annotation.DateFormat;
import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.updater.AbstractPropertyUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author qiaolin
 */

@Slf4j
@Component
public class DatePropertyUpdater extends AbstractPropertyUpdater<Date> {

    private static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    @Override
    public boolean support(Class<?> clazz) {
        return Date.class.isAssignableFrom(clazz);
    }

    @Override
    protected Date parseValue(PropertyInfo propertyInfo, ConfigChange change) {
        DateFormat annotation = propertyInfo.getField().getAnnotation(DateFormat.class);
        String format = (annotation == null) ? DEFAULT_DATE_FORMAT : annotation.value();

        try {
            return new SimpleDateFormat(format).parse(change.getNewValue().trim());
        } catch (ParseException e) {
            log.warn("格式化日期出错");
        }
        return null;
    }
}
