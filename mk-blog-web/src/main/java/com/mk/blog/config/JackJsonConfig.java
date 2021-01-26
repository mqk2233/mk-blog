package com.mk.blog.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe JackJson配置类
 * @author MK
 * @date 2021/1/25
 */
@Configuration
public class JackJsonConfig {

    /**
     * long序列化时转String避免精度丢失
     *
     * @return {@link Jackson2ObjectMapperBuilderCustomizer }
     * @author MK
     * @date 2021/1/25 1:02
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
    }
}
