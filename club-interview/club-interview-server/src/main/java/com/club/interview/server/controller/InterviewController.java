package com.club.interview.server.controller;

import com.club.interview.server.service.InterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tellsea
 * @date 2024−08−17
 */
@Slf4j
@RestController
@RequestMapping("/interview")
public class InterviewController {

    @Resource
    private InterviewService interviewService;

//    @Resource
//    private InterviewHistoryService interviewHistoryService;
//
//    @Resource
//    private InterviewQuestionHistoryService interviewQuestionHistoryService;





}
