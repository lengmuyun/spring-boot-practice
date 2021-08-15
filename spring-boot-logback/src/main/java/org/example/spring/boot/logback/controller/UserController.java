package org.example.spring.boot.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller
 *
 * @author fangkuangzhang
 * @date 2021/8/15 11:05
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/log")
    public String log() {
        log.info("打印INFO级别日志");
        log.warn("打印WARN级别日志");
        log.error("打印ERROR级别日志");
        return "log";
    }

}
