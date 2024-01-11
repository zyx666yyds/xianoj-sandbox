package com.zyx.zyxojsandbox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyx
 * @version 1.0
 * @date 2024/1/8 008 15:32
 */
@RestController("/")
public class MainController {

    @GetMapping("/health")
    public String getHealth() {
        return "ok";
    }
}
