package com.club.subject.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池的config管理
 * @author Tellsea
 * @date 2024−07−28
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 核心线程数
     */

    @Bean(name = "labelThreadPool")
    public ThreadPoolExecutor getLabelThreadPool() {
        return new ThreadPoolExecutor(10,100, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(40), new CustomNameThreadFactory("label"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    //使用name属性是可以方便以后想注册其他ThreadPoolExecutor类型的bean，如@Bean(name = "subjectThreadPool")
}
