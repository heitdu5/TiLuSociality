package com.club.interview.server.service;


import com.club.interview.api.req.InterviewReq;
import com.club.interview.api.req.InterviewSubmitReq;
import com.club.interview.api.req.StartReq;
import com.club.interview.api.vo.InterviewQuestionVO;
import com.club.interview.api.vo.InterviewResultVO;
import com.club.interview.api.vo.InterviewVO;

public interface InterviewService {

    InterviewVO analyse(InterviewReq req);

    InterviewQuestionVO start(StartReq req);

    InterviewResultVO submit(InterviewSubmitReq req);
}
