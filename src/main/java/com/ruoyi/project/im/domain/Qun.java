package com.ruoyi.project.im.domain;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @author by hujun
 * @date 2023-03-21
 */

@Data
public class Qun {
    private String id;
//    private String qunid;
//    private String chengyuanid;
    private String qunzhuid;
    private String quntouxiang;
    private String qunmingzi;
    private String compid;
    private String weizhi;

    private Date ucreatetime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Qun qun = (Qun) o;
        return qunmingzi.equals(qun.qunmingzi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qunmingzi);
    }
}
