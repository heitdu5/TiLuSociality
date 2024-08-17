package com.club.subject.application.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author heit
 * @date 2024−07−25
 */
@Component
@RocketMQMessageListener(topic = "first-test", consumerGroup = "test-consumer")
@Slf4j
public class TestConsumer  implements RocketMQListener<String> {


    @Override
    public void onMessage(String s) {
      log.info("接收到rocketmq了{}",s);
    }
}
