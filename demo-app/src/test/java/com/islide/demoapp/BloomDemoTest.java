package com.islide.demoapp;

import com.islide.demoapp.service.guava.BloomFilterDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoAppApplication.class)
public class BloomDemoTest {

    @Resource
    private BloomFilterDemo bloomFilterDemo;

    @Test
    public void check(){
        String key = "1000";
        bloomFilterDemo.check(key);
        try {
            Thread.sleep(2000l);
            key = "zhangsan";
            bloomFilterDemo.check(key);

            Thread.sleep(2000l);
            key = "zhangsan2";
            bloomFilterDemo.check(key);
            Thread.sleep(2000l);
            key = "zhangsan2";
            bloomFilterDemo.check(key);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
