package com.islide.demoapp.service.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.islide.demoapp.domain.Equipment;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 布隆过滤器demo
 */
@Service
public class BloomFilterDemo {

    private BloomFilter<CharSequence> bloomFilter;


    /**
     * 初始化布隆过滤器
     */
    @PostConstruct
    public void initBloomFilter() {

        bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")),1000000);
        List<String> list = new ArrayList<>();
        for (int i=0;i<100000;i++){
            list.add(i+"");
            bloomFilter.put(i+"");
        }


        bloomFilter.put("zhangsan");
    }


    public boolean check(String key){
        boolean flag =   bloomFilter.mightContain(key);
        if(!flag){
            System.out.println("不存在");
            bloomFilter.put(key);
        }else{
            System.out.println("存在");
        }
        return flag;
    }
}
