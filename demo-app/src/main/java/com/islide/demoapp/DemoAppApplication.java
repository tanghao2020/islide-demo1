package com.islide.demoapp;



import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



//@EnableDubboConfig
//@DubboComponentScan({"com.islide.demoapp.service.dubbo","com.islide.demoapp.service"})
@SpringBootApplication
@EnableScheduling
@MapperScan("com.islide.demoapp.mapper")
public class DemoAppApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoAppApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DemoAppApplication.class, args);
//        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("执行JVM ShutdownHook！！");
//            }
//        }));
    }

}
