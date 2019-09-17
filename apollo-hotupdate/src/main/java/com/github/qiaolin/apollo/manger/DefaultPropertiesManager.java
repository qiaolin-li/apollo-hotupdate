package com.github.qiaolin.apollo.manger;

import com.github.qiaolin.apollo.support.PropertyInfo;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

/**
 * 默认的属性管理器
 * <p>
 * 作用有两个
 * <ul>
 *     <li>它管理着所有需要被热更新的属性</li>
 *     <li>监听Apollo属性修改，然后寻找合适的更新器进行更新</li>
 * </ul>
 *
 * @author qiaolin
 */

@Slf4j
public class DefaultPropertiesManager implements HotUpdatePropertyManager {

    private Multimap<String, PropertyInfo> propertyMap = ArrayListMultimap.create();

    @Override
    public void registryPropertyInfo(PropertyInfo propertyInfo) {
        propertyMap.put(propertyInfo.getKey(), propertyInfo);
        log.info("热更新属性 注册成功 propertyKey:{}", propertyInfo.getKey());
    }

    @Override
    public void registryPropertyInfo(List<PropertyInfo> propertyInfos) {
        propertyInfos.forEach(this::registryPropertyInfo);
    }

    @Override
    public boolean containProperty(String propertyKey) {
        return propertyMap.containsKey(propertyKey);
    }

    @Override
    public Collection getPropertyInfo(String propertyKey) {
        return propertyMap.get(propertyKey);
    }


}
