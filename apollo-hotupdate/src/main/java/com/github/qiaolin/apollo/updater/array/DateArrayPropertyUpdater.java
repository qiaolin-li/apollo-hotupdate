package com.github.qiaolin.apollo.updater.array;

import com.github.qiaolin.apollo.annotation.DateFormat;
import com.github.qiaolin.apollo.exception.UpdatePropertyException;
import com.github.qiaolin.apollo.support.PropertyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Enum 数组类型更新器
 * @author qiaolin
 */
@Slf4j
@Component
public class DateArrayPropertyUpdater extends AbstractArrayPropertyUpdater<Date> {

    private static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    @Override
    public boolean support(Class<?> clazz) {
        return Date[].class.isAssignableFrom(clazz) ;
    }

    @Override
    protected Date formatItemValue(String value, PropertyInfo propertyInfo) {
        DateFormat annotation = propertyInfo.getField().getAnnotation(DateFormat.class);
        String format = (annotation == null) ? DEFAULT_DATE_FORMAT : annotation.value();

        try {
            return new SimpleDateFormat(format).parse(value);
        } catch (ParseException e) {
            log.warn("格式化日期出错");
            throw new UpdatePropertyException("日期格式化错误");
        }
    }

}
