package com.islide.demoapp;

import com.islide.demoapp.domain.Equipment;
import com.islide.demoapp.service.loadingcache.LoadingCacheDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoAppApplication.class)
public class DemoAppApplicationTests {


    @Resource
    private LoadingCacheDemo loadingCacheDemo;

    @Test
    public void contextLoads() {
       // couponService.print();
        System.err.println("测试loadingCachedemo");
        try {
            for(int i=0;i<10;i++){
                List<Equipment> datas =  loadingCacheDemo.query("zhang");
                for (Equipment equiment : datas) {
                    System.out.println(equiment);
                }
            }
           System.out.println("所有数据");
           ConcurrentMap<String, List<Equipment>> map =  loadingCacheDemo.queryAll();
            map.forEach((k,v)->{
                System.out.println("key="+k+"    value="+v);
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
