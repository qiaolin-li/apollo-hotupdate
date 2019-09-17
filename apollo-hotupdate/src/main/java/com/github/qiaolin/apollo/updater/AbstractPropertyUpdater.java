package com.github.qiaolin.apollo.updater;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.github.qiaolin.apollo.support.PropertyInfo;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 *  抽象属性更新器
 *  <P>模板化更新数据
 * @param <T>
 * @author qiaolin
 */

@Slf4j
public abstract class AbstractPropertyUpdater<T> implements PropertyUpdater {

    @ApolloConfig
    private Config config;

    @Override
    public boolean update(PropertyInfo propertyInfo, ConfigChange change) {
        Field field = propertyInfo.getField();
        field.setAccessible(true);
        try {
            field.set(propertyInfo.getTarget(), parseValue(propertyInfo, change));
            return true;
        } catch (IllegalAccessException e) {
            log.warn("更新属性值失败 " +
                            "propertyName:{}, className:{}, fieldName:{}, oldValue:{}, newValue:{} ",
                    propertyInfo.getKey(), propertyInfo.getTarget().getClass().getName(),
                    change.getOldValue(), change.getNewValue(), e);
        }
        return false;
    }

    /**
     *  转换成属性需要的值
     * @param propertyInfo 包含了热更新属性的信息
     * @param change  当前属性变更的信息
     * @return  正确类型的新值
     */
    protected abstract T parseValue(PropertyInfo propertyInfo, ConfigChange change);

    /**
     *  apollo配置对象
     * @return
     */
    protected Config getConfig(){
        return config;
    }
}
