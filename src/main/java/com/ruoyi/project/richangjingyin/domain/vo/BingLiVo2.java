package com.ruoyi.project.richangjingyin.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-23
 */
@Data
public class BingLiVo2 {
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
    private List<String> productid;
    private List<String> num;
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
}
