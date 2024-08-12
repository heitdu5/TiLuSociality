package com.jingdianjichi.core.core.sdk;

import com.jingdianjichi.core.config.GenConfig;
import com.jingdianjichi.core.config.MapperConfig;
import org.apache.velocity.VelocityContext;

/**
 * 针对当前文件的上下文进行操作
 *
 * @author loser
 * @date 2023/9/4
 */
@FunctionalInterface
public interface PostCurFiledContextAware {

    void doAware(GenConfig genConfig, MapperConfig mapperConfig, VelocityContext context);

}
