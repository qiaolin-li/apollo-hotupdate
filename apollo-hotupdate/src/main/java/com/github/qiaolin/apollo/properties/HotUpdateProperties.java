package com.github.qiaolin.apollo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  热更新配置类
 * @author qiaolin
 */

@Getter
@Setter
@ConfigurationProperties("apollo.hot.update")
public class HotUpdateProperties {

    /**
     *  apollo 命名空间, 默认application
     *  <p>需要和你 @EnableApolloConfig(value = "application", order = 1)的value一致
    */
    private String[] namespaces = {"application"};

    /**
     *  解析 {@link ConfigurationProperties} 注解时，解析到他的第几层
     *  <p> 也就是对象中有对象，如果对象中的对象不为空，那么也会在热更新的监控范围
     */
    private int recursionLevel = 5;

    /**
     *  要热更新的属性匹配符
     *  <p> 支持表达式
     *  例如：
     *  <table border="1">
     *      <tr>
     *          <th width="15%">表达式</th>
     *          <th width="40%">匹配成功</th>
     *          <th width="40%">匹配失败</th>
     *      </tr>
     *      <tr>
     *          <td>**</td>
     *          <td>匹配所有</td>
     *          <td>无</td>
     *      </tr>
     *      <tr>
     *          <td>com.github.qiaolin.*</td>
     *          <td>com.github.qiaolin.a、com.github.qiaolin.xx、com.github.qiaolin.zzz</td>
     *          <td>com.github.qiaolin.a.xx、com.github.qiaolin.a.zz.xx</td>
     *      </tr>
     *      <tr>
     *          <td>com.github.qiaolin.**</td>
     *          <td>com.github.qiaolin.a、com.github.qiaolin.xx.bb、com.github.qiaolin.zzz.ss</td>
     *          <td>com.github.qiuyan.x、com.github.qiuyan.bb.xx</td>
     *      </tr>
     *  </table>
     *
     *  <p> 你可以不配置，但是它意味着扫描所有的属性，包含一些框架里面的属性
     *  <p> 可能会出现一些不好的情况，一些误操作，导致修改了框架里面的属性
     *  <p> 推荐你尽量配置他，只扫描你需要的属性
     */
    private String[] updateKeys = {"**"};
}
