package com.github.qiaolin.apollo.processor;

import com.github.qiaolin.apollo.manger.HotUpdatePropertyManager;
import com.github.qiaolin.apollo.resolver.PropertyResolver;
import com.github.qiaolin.apollo.support.PropertyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *  一个后置处理器，用于检查注册到IOC容器中的Bean是否存在要热更新的属性
 *  <p>
 *  主要的流程如下：
 *  <ul>
 *  <li>循环所有的属性解析器{@link PropertyResolver}，解析出bean中需要热更新的属性    </li>
 *  <li>将每个属性解析器解析出来的属性信息注册到属性管理器{@link HotUpdatePropertyManager}   </li>
 *  </ul>
 * @see PropertyResolver
 * @see HotUpdatePropertyManager
 * @author qiaolin
 */

@Slf4j
public class HotUpdatePropertyBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private List<PropertyResolver> propertyResolvers;

    @Autowired
    private HotUpdatePropertyManager propertyManager;


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (PropertyResolver resolver : propertyResolvers) {
            List<PropertyInfo> propertyInfoList = resolver.resolver(bean);
            if(!CollectionUtils.isEmpty(propertyInfoList)){
                // 注册到 属性管理器
                propertyManager.registryPropertyInfo(propertyInfoList);
            }
        }
        return bean;
    }


}
