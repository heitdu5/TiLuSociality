package com.club.wx.controller;


import com.club.wx.handler.WxChatMsgFactory;
import com.club.wx.handler.WxChatMsgHandler;
import com.club.wx.handler.WxChatMsgTypeEnum;
import com.club.wx.utils.MessageUtil;
import com.club.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;


@RestController
@Slf4j
public class CallBackController {

    private static final String token = "adwidhaidwoaid";


    @Resource
    private WxChatMsgFactory wxChatMsgFactory;


    /**
     * 回调消息校验
     */
    @GetMapping("callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("get验签请求参数：signature:{}，timestamp:{}，nonce:{}，echostr:{}",
                signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "unknown";
    }


    @PostMapping(value = "callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false)String msgSignature) {
        log.info("接收到微信消息：requestBody：{}", requestBody);

        Map<String, String> msgMap = MessageUtil.parseXml(requestBody);
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event") ==null ? "" : msgMap.get("Event");

        StringBuilder sb = new StringBuilder();
        sb.append(msgType);
        if (!StringUtils.isEmpty(event)){
            sb.append(".");
            sb.append(event);
        }
        String msgTypeKey = sb.toString();
        WxChatMsgHandler wxChatMsgHandler = wxChatMsgFactory.getHandlerByMsgType(msgTypeKey);
        if (Objects.isNull(wxChatMsgHandler)) {
            return "unknown";
        }
        String replyContent = wxChatMsgHandler.dealMsg(msgMap);
        log.info("replyContent:{}", replyContent);
        return replyContent;
    }
}


/**
 * String msg = "<xml>\n" +
 * //                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
 * //                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
 * //                "  <CreateTime>12345678</CreateTime>\n" +
 * //                "  <MsgType><![CDATA[text]]></MsgType>\n" +
 * //                "  <Content><![CDATA[不知道，反正涛哥是狗]]></Content>\n" +
 * //                "</xml>";
 * //
 * //        String example = "<xml>\n" +
 * //                "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
 * //                "  <FromUserName><![CDATA[FromUser]]></FromUserName>\n" +
 * //                "  <CreateTime>123456789</CreateTime>\n" +
 * //                "  <MsgType><![CDATA[event]]></MsgType>\n" +
 * //                "  <Event><![CDATA[subscribe]]></Event>\n" +
 * //                "</xml>";
 */