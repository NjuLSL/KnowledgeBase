package com.njulsl.knowledgebase.service;

import com.njulsl.knowledgebase.domain.Ebook;
import com.njulsl.knowledgebase.domain.EbookExample;
import com.njulsl.knowledgebase.mapper.EbookMapper;
import com.njulsl.knowledgebase.req.EbookReq;
import com.njulsl.knowledgebase.resp.EbookResp;
import com.njulsl.knowledgebase.util.CopyUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();

        criteria.andNameLike("%"+req.getName()+"%");
        List<Ebook> list = ebookMapper.selectByExample(ebookExample);

        List<EbookResp> ebookResps = CopyUtil.copyList(list, EbookResp.class);
        return ebookResps;
    }
}
