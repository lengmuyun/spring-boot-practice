### spring boot 集成 swagger 3.0

#### 1. 在POM文件引入相关依赖

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```

#### 2. spring boot swagger 配置类编写

> 配置类中需要配置扫描那些类，可以通过`springfox.documentation.spring.web.plugins.ApiSelectorBuilder.apis`指定扫描的包名或者被指定注解标注的方法。

```java
package org.example.spring.boot.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置
 *
 * @author fangkuangzhang
 * @date 2021/7/13 7:49
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo()).enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.example.spring.boot.swagger.controller"))
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring boot swagger项目接口文档")
                .description("spring boot 集成 swagger 3.0")
                .contact(new Contact("fkz", "作者URL", "971434648@qq.com"))
                .version("1.0")
                .build();
    }

}
```

#### 3. 编写测试的 controller，并使用 swagger 相关注解

> - 使用注解`@Api`标注在类`UserController`上，表明这是用户管理的模块；
> - 使用注解`@ApiOperation`标注在方法`org.example.spring.boot.swagger.controller.UserController.getById`上，表明这个接口是根据id查询用户信息；
> - 使用注解`@ApiImplicitParam`标注在方法`org.example.spring.boot.swagger.controller.UserController.getById`上，对方法的参数进行描述，如果有多个参数，使用注解`@ApiImplicitParams`。

```java
package org.example.spring.boot.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.example.spring.boot.swagger.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author fangkuangzhang
 * @date 2021/7/14 7:05
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataType = "Long")
    public UserEntity getById(@PathVariable Long id) {
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setName("aaaa");
        user.setAge(18);
        user.setEmail("aaaa@163.com");
        return user;
    }

}
```

> 使用注解`@ApiModel`和`@ApiModelProperty`标注返回值。

```java
package org.example.spring.boot.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息
 *
 * @author fangkuangzhang
 * @date 2021/7/14 7:15
 */
@Data
@NoArgsConstructor
@ApiModel("用户信息")
public class UserEntity {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("邮箱")
    private String email;

}
```

#### 4. 启动项目，并访问localhost:8080/swagger-ui/index.html

[![WZJdbT.png](https://z3.ax1x.com/2021/07/14/WZJdbT.png)](https://imgtu.com/i/WZJdbT)

#### 5. 确认接口信息渲染后，引入`swagger-bootstrap-ui`框架

##### 5.1 在POM文件引入`swagger-bootstrap-ui`依赖

```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>swagger-bootstrap-ui</artifactId>
    <version>1.9.6</version>
</dependency>
```

##### 5.2 启动项目，并访问localhost:8080/doc.html

[![WZJarV.png](https://z3.ax1x.com/2021/07/14/WZJarV.png)](https://imgtu.com/i/WZJarV)