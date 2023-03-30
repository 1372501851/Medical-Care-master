package com.ruoyi.common.timing;

import com.ruoyi.project.merchant.mapper.XtProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务，用于清空每（天、周、月、季、年）的商品销售记录
 * @date 2023-02-14
 */

@Service
public class TimingService {
//    @Autowired
//    private XtProductMapper productMapper;
//
//    @Scheduled(cron = "0 0 2 1/1 * ? *")
//    public void emptyDailySales(){
//        productMapper.emptyDailysales();
//    }
//
//    @Scheduled(cron = "0 0 2 1/7 * ? *")
//    public void hello(){
//        productMapper.emptyWeeklysales();
//    }
//
//    @Scheduled(cron = "0 0 2 1 1/1 ? *")
//    public void emptyMonthsales(){
//        productMapper.emptyMonthsales();
//    }
//
//    @Scheduled(cron = "0 0 2 1 1/3 ? *")
//    public void emptyQuartersales(){
//       productMapper.emptyQuartersales();
//    }
//
//    @Scheduled(cron = "0 0 2 1 1 ? *")
//    public void emptyAnnualsales(){
//        productMapper.emptyAnnualsales();
//    }
}
