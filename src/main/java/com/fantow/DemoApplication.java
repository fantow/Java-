package com.fantow;

import com.fantow.Spring练习.RedisTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        RedisTest bean = context.getBean(RedisTest.class);

        bean.test1();

    }
}
