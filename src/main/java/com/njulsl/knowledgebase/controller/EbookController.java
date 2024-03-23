package com.njulsl.knowledgebase.controller;

import com.njulsl.knowledgebase.req.EbookQueryReq;
import com.njulsl.knowledgebase.req.EbookSaveReq;
import com.njulsl.knowledgebase.resp.CommonResp;
import com.njulsl.knowledgebase.resp.EbookQueryResp;
import com.njulsl.knowledgebase.resp.PageResp;
import com.njulsl.knowledgebase.service.EbookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
}
