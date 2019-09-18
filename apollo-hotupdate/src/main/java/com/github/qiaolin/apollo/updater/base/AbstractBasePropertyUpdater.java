package com.github.qiaolin.apollo.updater.base;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.github.qiaolin.apollo.exception.UpdatePropertyException;
import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.updater.AbstractPropertyUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


/**
 * 数组抽象属性更新器
 * @author qiaolin
 */
@Slf4j
public abstract class AbstractBasePropertyUpdater<T> extends AbstractPropertyUpdater<T> {


    /**
     *  转换成属性需要的值
     * @param propertyInfo 包含了热更新属性的信息
     * @param change  当前属性变更的信息
     * @return  正确类型的新值
     */
    @Override
    protected T parseValue(PropertyInfo propertyInfo, ConfigChange change){
        try {
            return parseValue0(propertyInfo, change.getNewValue().trim());
        } catch (Exception e) {
            if(!StringUtils.isEmpty(propertyInfo.getDefaultValue())){
                log.warn("设置的值不正确，使用默认值！ propertyKey:{}, oldValue:{}, newValue:{}, defaultValue:{}",
                        propertyInfo.getKey(), change.getOldValue(), change.getNewValue(), propertyInfo.getDefaultValue(), e);
                return parseValue0(propertyInfo, propertyInfo.getDefaultValue().trim());
            }
            throw e;
        }
    }

    /**
     *  具体转换方法
     * @param propertyInfo
     * @param newValue
     * @return
     */
    protected abstract T parseValue0(PropertyInfo propertyInfo, String newValue);


}


