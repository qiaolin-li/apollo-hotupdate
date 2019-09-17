package com.github.qiaolin.apollo.listener;

import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.github.qiaolin.apollo.manger.HotUpdatePropertyManager;
import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.updater.PropertyUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * apollo 属性更新监听器
 * @author qiaolin
 */

@Slf4j
public class ApolloPropertyUpdateListener implements ConfigChangeListener {

    @Autowired
    private HotUpdatePropertyManager propertyManager;

    @Autowired
    private List<PropertyUpdater> propertyUpdaters;


    @Override
    public void onChange(ConfigChangeEvent changeEvent) {
        Set<String> changedKeys = changeEvent.changedKeys();
        for (String changedKey : changedKeys) {
            log.info("开始更新：property:{}", changedKey);
            if(propertyManager.containProperty(changedKey)){
                update(changedKey, changeEvent.getChange(changedKey));
            }else{
                log.warn("在热更新的属性中没有找到 property:{} , 无法更新", changedKey);
            }
        }
    }


    /**
     *  更新属性
     * @param changedKey
     * @param change
     * @return
     */
    private boolean update(String changedKey, ConfigChange change){
        Collection<PropertyInfo> propertyInfos = propertyManager.getPropertyInfo(changedKey);
        propertyInfos.forEach(p ->{
            PropertyUpdater propertyUpdater = getPropertyUpdater(p.getField().getType());
            if(propertyUpdater != null){
                doUpdate(propertyUpdater, p, changedKey, change);
            }else{
                log.warn("找不到更新器 propertyKey:{}, oldValue:{}, newValue:{}, nameSpace:{}, changeType:{} ",
                        changedKey, change.getOldValue(), change.getNewValue(), change.getNamespace(), change.getChangeType());
            }
        });
        return true;
    }

    /**
     *  执行更新
     * @param propertyUpdater
     * @param propertyInfo
     * @param changedKey
     * @param change
     */
    private void doUpdate(PropertyUpdater propertyUpdater, PropertyInfo propertyInfo, String changedKey, ConfigChange change){
        try {
            if(propertyUpdater.update(propertyInfo, change)){
                log.info("更新配置 {} 成功 type:{}, oldValue：{}, newValue:{}",
                        changedKey, change.getChangeType(), change.getOldValue(), change.getNewValue());
            }else{
                log.warn("更新配置 {} 失败 type:{}, oldValue：{}, newValue:{}",
                        changedKey, change.getChangeType(), change.getOldValue(), change.getNewValue());
            }
        } catch (Exception e) {
            log.warn("更新配置 {} 异常 type:{}, oldValue：{}, newValue:{}",
                    changedKey, change.getChangeType(), change.getOldValue(), change.getNewValue(), e);
        }

    }

    /**
     *  获取属性更新器
     * @param type 当前要更新属性的类型
     * @return
     */
    private PropertyUpdater getPropertyUpdater(Class<?> type){
        for (PropertyUpdater propertyUpdater : propertyUpdaters) {
            if(propertyUpdater.support(type)){
                return propertyUpdater;
            }
        }
        return null;
    }

}
