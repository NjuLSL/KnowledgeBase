package com.njulsl.knowledgebase.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.njulsl")
@SpringBootApplication
@MapperScan("com.njulsl.knowledgebase.mapper")
@EnableScheduling
@EnableAsync
public class KnowledgeBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgeBaseApplication.class, args);
    }

}
