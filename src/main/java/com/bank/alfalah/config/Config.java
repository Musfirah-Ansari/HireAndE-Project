package com.bank.alfalah.config;

import com.bank.alfalah.security.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelMapper() {
        return new org.modelmapper.ModelMapper();
    }

    @Bean
    public StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        jwtTokenUtil.setSecret("your-secret-key"); // Replace with environment-specific value
        jwtTokenUtil.setIssuer("your-issuer");
        jwtTokenUtil.setJwtExpirationInMs(3600000); // Replace with actual values
        jwtTokenUtil.setRefreshExpirationDateInMs(7200000);
        return jwtTokenUtil;
    }
}
