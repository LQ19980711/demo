package com.example.demo.util.Redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class RedisTestComponent {

    @Resource
    private RedisTemplate redisTemplate;

    public void testSave(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("total",1);
    }
}
