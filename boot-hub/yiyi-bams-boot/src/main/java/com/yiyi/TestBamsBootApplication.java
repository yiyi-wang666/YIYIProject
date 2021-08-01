package com.yiyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TestBamsBootApplication {

    public static void main(String[] args) {
        ///ShutdownHookThread shutdownHookThread = new ShutdownHookThread(Thread.currentThread());
        SpringApplication.run(TestBamsBootApplication.class, args);
    }

    @RequestMapping("/index")
    public String index(){
        return "success to start app com.yiyi.BamsBootApplication";
    }


}
