package com.ruoyi.project.richangjingyin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author by hujun
 * @date 2023-02-23
 */
@Data
public class XtBingLi {
    private String id;
    private String idcard;
    private String hospital;
    private String keshi;
    private String yishenguserid;
    private String huangzheuserid;
    private Date chushendate;
    private String sex;
    private Integer age;
    private String ismarry;
    private String guoming;
    private String binghistory;
    private String linchuang;
    private String productid;
    private String utype;

    private String huanzhename;

    private String yishengname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dayintime;

    private String huangzhezhusubingqing;

    private String huangzhedianhua;

    private String shengao;
    private String tizhong;
    private String menzhenhao;
    private String yewushenhe;
    private String jiuzhenhao;
    private String jianchaxiangmu;
    private String productnum;

    private String haocainame;
    private String haocaidanjia;
    private String haocainum;
    private String jianchaname;
    private String jianchandanjia;
    private String jianchanum;
    private String zhiliaoname;
    private String zhiliaodanjia;
    private String zhiliaonum;
    private String hulifei;
    private String saas;
    private String qita;





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XtBingLi xtBingLi = (XtBingLi) o;
        return Objects.equals(id, xtBingLi.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
