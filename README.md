# apollo-hotupdate
apollo配置中心属性热更新


在使用Apollo配置中心的时候，我们时常需要自己写热更新属性的代码！好像Apollo也提供了热更新，但是只支持 @Value注解的。然后我自己写了一个自动更新的组件 **apollo-hotupdate**，暂时只支持spring boot，下面带大家使用一下

github仓库：https://github.com/qiaolin-li/apollo-hotupdate

## 1、集成
使用maven只需要引入下面的坐标：
```xml
<dependency>
    <groupId>com.github.qiaolin</groupId>
    <artifactId>apollo-hotupdate</artifactId>
    <version>1.0.3</version>
</dependency>
```


## 2、使用

#### 配置项
```properties
# apollo命名空间，数组，多个以","分割, 默认 application
apollo.hot.update.namespaces= application

# ConfigurationProperties 注解所在的类属性为对象类型，则会递归解析，这里为递归的层数
# 默认为 5层
apollo.hot.update.recursion-level= 5 

# 那些注解要被热更新，推荐配置一下，默认 ** 即所有配置
# 如果你的注解都为 xxx. 开头，那么你配置 xxx.**即可
# 支持多个值，以","分割，例如：com.github.**, com.gitlab.**
apollo.hot.update.update-keys= com.github.**
```


#### 目前支持的注解：
###### @Value 注解
```java
@Component
public class MyService {

    @Value(value = "${com.github.qiaolin.a.b}")
    private String b;
    
}

```

###### @ConfigurationProperties 注解
```java
/**
 * 测试基本类型
 * @author qiaolin
 */
 
@Getter
@Setter
@ConfigurationProperties(prefix = "com.github.qiaolin")
public class MyProperties {

    @DefaultValue("1")
    private Integer prpo1;

//    @DateFormat("yyyy-MM-dd HH:mm:ss") 定制格式化，一般用不到
    private Date prpo10;

}
```


##### @HotUpdateField 注解（自定义）
 ```java
@Component
public class MyService {

    @HotUpdateFiled(value = "com.github.qiaolin.day")
    private int day;

    @HotUpdateFiled(prefix = "com.github.qiaolin", value = "a")
    private String a;
    
}
```
注意的是，这个自定义注解在spring 启动时不会给你装配值...，因为是自定义的，但是在在apollo修改值时可以同步上，现在我也不知道他能在哪里被用上...，或许有某种场景需要默认值写死到这个属性，然后偶尔需要修改，但是希望他重启后还是回到那个原来的值...


#### 目前支持热更新的类型：
```
    	Byte、    byte、    Byte[]、    byte   
        Character、    char、    Character[]、    char   
        Short、    short、    Short[]、    short   
        Integer、    int、    Integer[]、    int   
        Float、    float、    Float[]、    float   
        Long、    long、    Long[]、    long   
        Double、    double、    Double[]、    double   
        Boolean、    boolean、    Boolean[]、    boolean   
        String、    String[]   
        Enum、	Enum[]
       
```

### 默认值
注意：默认值都是String格式的，值一定要和自己的类型匹配
#### 使用@Value注解时,写到:后面
```java
    @Value(value = "${com.github.qiaolin.a.b:hehe}")
    private String b;
```

#### 使用@ConfigurationProperties注解时
配合自定义注解     @DefaultValue("1")
```java
@ConfigurationProperties(prefix = "com.github.qiaolin")
public class MyProperties {

    @DefaultValue("1")
    private Integer prpo1;

}
```

#### 使用@HotUpdateField注解
利用其 defaultValue 属性即可
```java
    @HotUpdateFiled(value = "com.github.qiaolin.day", defaultValue = "9")
    private int day;
```


 其他：
 参考Demo : https://github.com/qiaolin-li/apollo-hotupdate/tree/master/apollo-hotupdate-demo
