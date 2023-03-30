package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by hujun
 * @date 2023-03-16
 */

public class Search {

    public static List search(String name,List<String> list){
        List results = new ArrayList();
        Pattern pattern = Pattern.compile(name);
        for(int i=0; i < list.size(); i++){
            Matcher matcher = pattern.matcher((list.get(i)));
            if(matcher.find()){
                results.add(list.get(i));
            }
        }
        return results;
    }
}
