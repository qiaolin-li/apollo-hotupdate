package com.github.qiaolin.apollo.updater.array;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.github.qiaolin.apollo.support.PropertyInfo;
import com.github.qiaolin.apollo.updater.AbstractPropertyUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.stream.IntStream;


/**
 * Enum 数组类型更新器
 * @author qiaolin
 */
@Slf4j
@Component
public class EnumArrayPropertyUpdater extends AbstractPropertyUpdater<Enum[]> {

    @Override
    public boolean support(Class<?> clazz) {
        return Enum[].class.isAssignableFrom(clazz) ;
    }


    @Override
    protected Enum[] parseValue(PropertyInfo propertyInfo, ConfigChange change) {
        Class<Enum[]> type = (Class<Enum[]>) propertyInfo.getField().getType();
        String[] arrayProperty = getConfig().getArrayProperty(change.getPropertyName(), ",", new String[]{});

        Enum[] array = (Enum[]) Array.newInstance(type.getComponentType(), arrayProperty.length);

        IntStream.range(0, arrayProperty.length).forEach(i ->{
            array[i] = Enum.valueOf((Class<Enum>)type.getComponentType(), arrayProperty[i].trim());
        });

        return array;
    }
}
