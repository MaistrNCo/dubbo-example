package me.maistrenko.dubbosvc;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("me.maistrenko")
@EnableDubbo
public class DubboSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSvcApplication.class, args);
    }

}
