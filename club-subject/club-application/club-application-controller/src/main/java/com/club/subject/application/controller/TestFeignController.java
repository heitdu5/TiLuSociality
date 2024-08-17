package com.club.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.club.subject.common.entity.PageResult;
import com.club.subject.infra.basic.entity.SubjectInfoEs;
import com.club.subject.infra.basic.service.SubjectEsService;
import com.club.subject.infra.rpc.UserRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestFeignController {


    @Resource
    private SubjectEsService subjectEsService;

    @Resource
    private UserRpc userRpc;

    @PostMapping("/querySubjectByKeyWord")
    public void querySubjectByKeyWord() {
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setKeyWord("redis                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ");
        PageResult<SubjectInfoEs> subjectInfoEsPageResult = subjectEsService.querySubjectList(subjectInfoEs);
        log.info("querySubjectByKeyWord:{}", JSON.toJSONString(subjectInfoEsPageResult));
    }


}
