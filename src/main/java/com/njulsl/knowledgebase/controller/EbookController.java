package com.njulsl.knowledgebase.controller;

import com.njulsl.knowledgebase.domain.Ebook;
import com.njulsl.knowledgebase.resp.CommonResp;
import com.njulsl.knowledgebase.service.EbookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @GetMapping("/list")
    public CommonResp list()
    {
        CommonResp<List<Ebook>> commonResp = new CommonResp<>();
        List<Ebook> list = ebookService.list();
        commonResp.setContent(list);
        return commonResp;
    }
}
