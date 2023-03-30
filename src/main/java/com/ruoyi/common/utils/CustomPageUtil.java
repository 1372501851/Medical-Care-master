package com.ruoyi.common.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/1/13
 */
@Component
public class CustomPageUtil<T>{

    public List<T> getPageList(List<T> list,Integer pageNum,Integer pageSize){
        List<T> result = new ArrayList<>();
        int index = (pageNum - 1) * pageSize;
        int endIndex = index + pageSize;
        int size = list.size();
        for (int i = index; i < (Math.min(size, endIndex)); i++) {
            result.add(list.get(i));
        }
        return result;
    }

}
