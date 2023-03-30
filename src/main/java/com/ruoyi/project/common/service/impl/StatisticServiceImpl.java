package com.ruoyi.project.common.service.impl;

import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.common.domain.XtFinance;
import com.ruoyi.project.common.domain.statistic.*;
import com.ruoyi.project.common.service.IXtFinanceService;
import com.ruoyi.project.common.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/2/3
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private IXtFinanceService xtFinanceService;

    @Override
    public List<Statistic> getStatisticTable() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Statistic> result = new ArrayList<>();
        Statistic FinanceIncome = new Statistic();
        Statistic Financepayment = new Statistic();
        List<XtFinance> xtFinances = xtFinanceService.selectXtFinanceList(new XtFinance());
        //财务收入统计
        FinanceIncome.setTitle(new Title("财务收入统计"));
        XAxis xAxis = new XAxis();
//        YAxis yAxis = new YAxis();
        FinanceIncome.setYAxis(new HashMap());
        List<String> dateList = new ArrayList<>();
        List<Series> seriesList = new ArrayList<>();
        Series series = new Series();
        series.setName("金额");
        series.setType("bar");
        List<String> dataList = new ArrayList<>();
        for (XtFinance xtFinance : xtFinances) {
            String date = format.format(xtFinance.getCreateTime());
            dateList.add(date);
            dataList.add(xtFinance.getAmount().toString());
        }
        series.setData(dataList);
        xAxis.setData(dateList);
        seriesList.add(series);
        FinanceIncome.setXAxis(xAxis);
        FinanceIncome.setSeries(seriesList);
        result.add(FinanceIncome);
        return result;
    }

    @Override
    public List<Map<String,Object>> getStatisticTable2() {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        List<Map<String,Object>> result = new ArrayList<>();
        //财务收入统计
        List<XtFinance> xtFinances = xtFinanceService.selectXtFinanceList(new XtFinance());
        xtFinances.sort(Comparator.comparing(BaseEntity::getCreateTime));
        List<String> dateList = new ArrayList<>();
        List<BigDecimal> dataList = new ArrayList<>();
        List<String> dateOutList = new ArrayList<>();
        List<BigDecimal> dataOutList = new ArrayList<>();
        for (XtFinance xtFinance : xtFinances) {
            if (xtFinance.getTradeType().equals("1")) {
                //财务收入
                String date = format.format(xtFinance.getCreateTime());
                int i = dateList.indexOf(date);
                if (i == -1) {
                    dateList.add(date);
                    dataList.add(xtFinance.getAmount());
                } else {
                    BigDecimal add = dataList.get(i).add(xtFinance.getAmount());
                    dataList.set(i,add);
                }
            }else {
                //财务支出
                String date = format.format(xtFinance.getCreateTime());
                int i = dateOutList.indexOf(date);
                if (i == -1) {
                    dateOutList.add(date);
                    dataOutList.add(xtFinance.getAmount());
                } else {
                    BigDecimal add = dataOutList.get(i).add(xtFinance.getAmount());
                    dataOutList.set(i,add);
                }
            }
        }
        Map<String,Object> financeIncome = new HashMap<>();
        financeIncome.put("title","2023财务收入统计");
        financeIncome.put("name","金额");
        financeIncome.put("type","bar");
        financeIncome.put("date",dateList);
        financeIncome.put("data",dataList);
        result.add(financeIncome);
        //财务支出统计
        Map<String,Object> financePayment = new HashMap<>();
        financePayment.put("title","2023财务支出统计");
        financePayment.put("name","金额");
        financePayment.put("type","bar");
        financePayment.put("date",dateOutList);
        financePayment.put("data",dataOutList);
        result.add(financePayment);
        return result;
    }

}
