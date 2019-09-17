package com.github.qiaolin.apollo.manger;

import com.github.qiaolin.apollo.support.PropertyInfo;
import java.util.Collection;
import java.util.List;

/**
 *  apollo 属性管理器
 * @author qiaolin
 */

public interface HotUpdatePropertyManager {

    /**
     *  这里注册一个 待更新的属性的原因就是开放给用户
     * @param propertyInfo
     */
    void registryPropertyInfo(PropertyInfo propertyInfo);

    /**
     *  注册属性信息
     * @param propertyInfos
     */
    void registryPropertyInfo(List<PropertyInfo> propertyInfos);

    /**
     *  属性是否需要热更新
     * @param propertyKey
     * @return
     */
    boolean containProperty(String propertyKey);

    /**
     *  获取热更新属性信息
     * @param propertyKey
     * @return
     */
    Collection getPropertyInfo(String propertyKey);
}
