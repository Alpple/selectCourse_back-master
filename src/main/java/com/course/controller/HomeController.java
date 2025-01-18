package com.course.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController extends BaseController{
    @RequestMapping("/")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/test")
    public Object test() {
        return "你好，世界！";
    }
}
