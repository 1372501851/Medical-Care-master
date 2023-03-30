package com.ruoyi.project.richangjingyin.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @author by hujun
 * @date 2023-02-24
 */
@Data
public class BingLiVo {

    private String hospital;
    private String keshi;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createtime;

    private String yishengname;

    private String jianchaxiangmu;

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        BingLiVo bingLiVo = (BingLiVo) o;
        return hospital.equals(bingLiVo.hospital) && keshi.equals(bingLiVo.keshi) && yishengname.equals(bingLiVo.getYishengname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(hospital, keshi ,yishengname);
    }
}
