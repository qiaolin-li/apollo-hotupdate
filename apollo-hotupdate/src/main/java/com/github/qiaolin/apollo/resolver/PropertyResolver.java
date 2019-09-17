package com.github.qiaolin.apollo.resolver;

import com.github.qiaolin.apollo.support.PropertyInfo;

import java.util.List;

/**
 *  属性解析器
 *  <p>
 *  解析bean中需要热更新的属性，支持自我扩展
 *  <p>
 *  关于如何扩展，请参考 {@link ConfigurationPropertiesResolver}、{@link HotUpdateFieldResolver}
 *
 * @author qiaolin
 */
public interface PropertyResolver {

    /**
     *  从bean中解析需要热更新的属性
     * @param bean 待解析的bean
     * @return
     */
    List<PropertyInfo> resolver(Object bean);


}
