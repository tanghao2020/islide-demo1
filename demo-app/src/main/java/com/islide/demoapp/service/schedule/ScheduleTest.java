package com.islide.demoapp.service.schedule;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.islide.demoapp.domain.Equipment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTest {


    @Scheduled(cron = "0/1 * * * * ?")
    public void job(){
        System.out.println("进入job");
    }
}
