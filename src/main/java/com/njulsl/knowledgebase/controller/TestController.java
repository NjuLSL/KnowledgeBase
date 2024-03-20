package com.njulsl.knowledgebase.controller;

import com.njulsl.knowledgebase.domain.Test;
import com.njulsl.knowledgebase.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/hello")
    public String hello()
    {
        return "hello world!";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World! Post，" + name;
    }

    @GetMapping("/test/list")
    public List<Test> list()
    {
        return testService.list();
    }

}
