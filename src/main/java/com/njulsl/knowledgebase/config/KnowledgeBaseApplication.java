package com.njulsl.knowledgebase.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.njulsl")
@SpringBootApplication
@MapperScan("com.njulsl.knowledgebase.mapper")
public class KnowledgeBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgeBaseApplication.class, args);
    }

}
