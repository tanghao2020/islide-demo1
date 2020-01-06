package com.islide.demoapp.service.loadingcache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.islide.demoapp.domain.Equipment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * loadingCachedemo
 */
@Service
public class LoadingCacheDemo {

    private static int cont = 0;

    LoadingCache<String, List<Equipment>> couponCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES) //设置过期时间
            .refreshAfterWrite(5,TimeUnit.MINUTES)  // 设置刷新缓存时间
            .build(new CacheLoader<String, List<Equipment>>() {
                @Override
                public List<Equipment> load(String key) throws Exception {
                    return loadEquipment(key);
                }
            });

    /**
     * 逻辑获取设置数据
     * @param key
     * @return
     */
    private List<Equipment> loadEquipment(String key) {
        System.out.println(key+"进入loadingCache添加数据class，来获取值" );
        Equipment data = new Equipment();
        cont++;
        data.setName(key + cont);
        List<Equipment> datas = new ArrayList<>();
        datas.add(data);
        return datas;
    }


    public List<Equipment> query(String key) throws ExecutionException {

        return couponCache.get(key);
    }

    public ConcurrentMap<String, List<Equipment>> queryAll(){
        return couponCache.asMap();
    }
}
