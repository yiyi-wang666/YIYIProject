package yiyi.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TestSpringBootApplication {

    public static void main(String[] args) {
        ///ShutdownHookThread shutdownHookThread = new ShutdownHookThread(Thread.currentThread());
        SpringApplication.run(TestSpringBootApplication.class, args);
    }

    @RequestMapping("/index")
    public String index(){
        return "success to start app com.yiyi.BamsBootApplication";
    }


}
