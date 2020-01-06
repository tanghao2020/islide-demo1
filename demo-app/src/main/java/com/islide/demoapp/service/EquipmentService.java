package com.islide.demoapp.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.islide.demoapp.domain.Equipment;
import com.islide.demoapp.mapper.EquipmentMapper;
import com.islide.demoapp.domain.EquipmentExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EquipmentService {
    @Resource
    private EquipmentMapper equipmentMapper;
    LoadingCache<String, List<Equipment>> equipmentCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)//过期时间   缓存中数值在指定时间没有值更新会过期
            .refreshAfterWrite(5,TimeUnit.MINUTES)// refreshAfterWrites这种使用异步刷新缓存  5分钟刷新一次
            //  expireAfterWrites  每次缓存失效LoadingCache都会去调用我们实现的load方法去重新加载缓存，在加载期间，所有线程对该缓存key的访问都将被block
            .build(new CacheLoader<String, List<Equipment>>() {
                @Override
                public List<Equipment> load(String key) throws Exception {
                    return loadEquipment(key);
                }
            });

    /**
     * 查询数据库
     * @param key   名称.
     * @return
     */
    private List<Equipment> loadEquipment(String key) {
//        EquipmentExample example = new EquipmentExample();
//        example.createCriteria().andNameEqualTo(key);
//        return equipmentMapper.selectByExample(example);


        return equipmentMapper.selectByLoginDate(key);
    }


}
