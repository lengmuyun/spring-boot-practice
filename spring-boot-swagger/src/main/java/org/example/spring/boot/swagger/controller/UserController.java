package org.example.spring.boot.swagger.controller;

import io.swagger.annotations.Api;
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
    public UserEntity getById(@PathVariable Long id) {
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setName("aaaa");
        user.setAge(18);
        user.setEmail("aaaa@163.com");
        return user;
    }

}
