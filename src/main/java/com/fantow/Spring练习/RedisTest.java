package com.fantow.Spring练习;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    LettuceConnectionFactory lettuceConnectionFactory;

    public void test1(){

        RedisConnection connection = lettuceConnectionFactory.getConnection();
        byte[] bytes = connection.get("k1".getBytes());
        System.out.println(new String(bytes));

//        redisTemplate.getConnectionFactory().getSentinelConnection();
//
//
//        ValueOperations opsForValue = redisTemplate.opsForValue();
//
//        HashOperations hashOperations = stringRedisTemplate.opsForHash();
//
////        opsForValue.setIfAbsent("k1","v1");
////
////        System.out.println("存放结束...");
////
////        String v1 = (String) opsForValue.get("k1");
////        System.out.println(v1);
////
////        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
////
////        connection.set("k2".getBytes(),"v2".getBytes());
//
//
//        hashOperations.put("test1","name","aaa");
//        hashOperations.put("test1","age","123");
//
//
//        Map<String,String> map = hashOperations.entries("test1");
//
//        for(Map.Entry<String,String> entry : map.entrySet()){
//            System.out.println("key: " + entry.getKey() + " -> " + " value:" + entry.getValue());
//        }
    }


    @Configuration
    class AppConfig{
        @Bean
        public LettuceConnectionFactory redisConnectionFactory(){
            System.out.println("123");
            return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost",6379));
        }
    }

}
