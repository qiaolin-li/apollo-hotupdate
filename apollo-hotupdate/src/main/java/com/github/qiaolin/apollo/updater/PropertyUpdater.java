package com.github.qiaolin.apollo.updater;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.github.qiaolin.apollo.support.PropertyInfo;

/**
 * 属性更新器
 * @author Administrator
 */
public interface PropertyUpdater {

    /**
     * 支持的类型
     * @param clazz
     * @return
     */
    boolean support(Class<?> clazz);

    /**
     *  更新属性
     * @param propertyInfo
     * @param change
     * @return
     */
    boolean update(PropertyInfo propertyInfo, ConfigChange change);

}
