package com.njulsl.knowledgebase.controller;

import com.njulsl.knowledgebase.req.EbookReq;
import com.njulsl.knowledgebase.resp.CommonResp;
import com.njulsl.knowledgebase.resp.EbookResp;
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
    public CommonResp list(EbookReq req)
    {
        CommonResp<List<EbookResp>> commonResp = new CommonResp<>();
        List<EbookResp> list = ebookService.list(req);
        commonResp.setContent(list);
        return commonResp;
    }
}
