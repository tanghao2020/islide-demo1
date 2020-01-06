package com.islide.demoapp.controllor;

import com.islide.demoapp.domain.Equipment;
import com.islide.demoapp.mapper.EquipmentMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * HTTP接口
 */
@RestController
public class EquipmentControllor {

    @Resource
    private EquipmentMapper equipmentMapper;

    /**
     * 添加测试数据
     */
    @GetMapping("/testdemo")
    public void putData(){

        String huawei = "huawei";
        String oppo = "oppo";
        String apple = "apple";
        for (int i = 0; i <1000000 ; i++) {
            Equipment equipment = new Equipment();
            Random random = new Random();
            int num = random.nextInt(5000);
            String name = "";
            if(num%3==0){
                name = huawei+num;
            }else if(num%3==1){
                name = oppo+num;
            }else{
                name = apple+num;
            }
            equipment.setName(name);
            //日期设置随机
            //一年之内的随机2020年1月1日14:33:59  时间。
            Date date = randomDate("2019-01-01 ","2019-12-31");
            equipment.setLoginTime(date);
            equipmentMapper.insertSelective(equipment);
            System.out.println("enter hello job!");
        }
    }

    private static Date randomDate(String beginDate,String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }

}
