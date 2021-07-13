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
