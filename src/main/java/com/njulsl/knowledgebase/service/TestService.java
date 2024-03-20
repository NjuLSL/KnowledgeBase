package com.njulsl.knowledgebase.service;

import com.njulsl.knowledgebase.domain.Test;
import com.njulsl.knowledgebase.mapper.TestMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list()
    {
        return testMapper.list();
    }
}
