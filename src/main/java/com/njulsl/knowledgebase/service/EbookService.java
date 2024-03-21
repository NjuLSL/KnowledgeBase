package com.njulsl.knowledgebase.service;

import com.njulsl.knowledgebase.domain.Ebook;
import com.njulsl.knowledgebase.mapper.EbookMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list() {
        return ebookMapper.selectByExample(null);
    }
}
