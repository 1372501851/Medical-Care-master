package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by hujun
 * @date 2023-03-14
 */

public class OO {

    public static List<?> subList(ArrayList<?> list, int pageSize, int pageNum) {
        int count = list.size(); // 总记录数
        // 计算总页数
        int pages = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        // 起始位置
        int start = pageNum <= 0 ? 0 : (pageNum > pages ? (pages - 1) * pageSize : (pageNum - 1) * pageSize);
        // 终止位置
        int end = pageNum <= 0 ? (pageSize <= count ? pageSize : count) : (pageSize * pageNum <= count ? pageSize * pageNum : count);
        return list.subList(start, end);
    }
}
