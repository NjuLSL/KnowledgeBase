package com.njulsl.knowledgebase.service;

import com.njulsl.knowledgebase.domain.Demo;
import com.njulsl.knowledgebase.mapper.DemoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list() {
        return demoMapper.selectByExample(null);
    }
}
