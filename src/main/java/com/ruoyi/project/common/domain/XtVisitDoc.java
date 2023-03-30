package com.ruoyi.project.common.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 就诊单据对象 xt_visit_doc
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public class XtVisitDoc
{

    /** 就诊单证ID */
    private String uvisitDocid;

    /** 患者ID */
    @Excel(name = "患者ID")
    private String userid;

    /** 医院ID(商家） */
    @Excel(name = "医院ID(商家）")
    private String ucompid;

    /** 医生ID */
    @Excel(name = "医生ID")
    private String uemployeeid;

    /** 部门（科室）ID */
    @Excel(name = "部门", readConverterExp = "科=室")
    private String udepartmentid;

    /** 就诊医院 */
    @Excel(name = "就诊医院")
    private String uhospital;

    /** 就诊科室 */
    @Excel(name = "就诊科室")
    private String uvisitingDepartment;

    /** 就诊医生 */
    @Excel(name = "就诊医生")
    private String uvisitingDoctor;

    /** 病历编号 */
    @Excel(name = "病历编号")
    private String umedicalRecordNo;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String upatientName;

    /** 性别 */
    @Excel(name = "性别")
    private String usex;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long uage;

    /** 婚否 */
    @Excel(name = "婚否")
    private String umarriage;

    /** 患者过敏史 */
    @Excel(name = "患者过敏史")
    private String upatientAllergyHistory;

    /** 患者病史 */
    @Excel(name = "患者病史")
    private String upatientMedicalHistory;

    /** 主述病症 */
    @Excel(name = "主述病症")
    private String umainSymptoms;

    /** 临床（初步）诊断 */
    @Excel(name = "临床", readConverterExp = "初=步")
    private String clinicalDiagnosis;

    /** Rp: 处方子 */
    @Excel(name = "Rp: 处方子")
    private String urp;

    /** 创建时间（时间戳） */
    @Excel(name = "创建时间", readConverterExp = "时=间戳")
    private Long ucreatedate;

    public void setUvisitDocid(String uvisitDocid)
    {
        this.uvisitDocid = uvisitDocid;
    }

    public String getUvisitDocid()
    {
        return uvisitDocid;
    }
    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public String getUserid()
    {
        return userid;
    }
    public void setUcompid(String ucompid)
    {
        this.ucompid = ucompid;
    }

    public String getUcompid()
    {
        return ucompid;
    }
    public void setUemployeeid(String uemployeeid)
    {
        this.uemployeeid = uemployeeid;
    }

    public String getUemployeeid()
    {
        return uemployeeid;
    }
    public void setUdepartmentid(String udepartmentid)
    {
        this.udepartmentid = udepartmentid;
    }

    public String getUdepartmentid()
    {
        return udepartmentid;
    }
    public void setUhospital(String uhospital)
    {
        this.uhospital = uhospital;
    }

    public String getUhospital()
    {
        return uhospital;
    }
    public void setUvisitingDepartment(String uvisitingDepartment)
    {
        this.uvisitingDepartment = uvisitingDepartment;
    }

    public String getUvisitingDepartment()
    {
        return uvisitingDepartment;
    }
    public void setUvisitingDoctor(String uvisitingDoctor)
    {
        this.uvisitingDoctor = uvisitingDoctor;
    }

    public String getUvisitingDoctor()
    {
        return uvisitingDoctor;
    }
    public void setUmedicalRecordNo(String umedicalRecordNo)
    {
        this.umedicalRecordNo = umedicalRecordNo;
    }

    public String getUmedicalRecordNo()
    {
        return umedicalRecordNo;
    }
    public void setUpatientName(String upatientName)
    {
        this.upatientName = upatientName;
    }

    public String getUpatientName()
    {
        return upatientName;
    }
    public void setUsex(String usex)
    {
        this.usex = usex;
    }

    public String getUsex()
    {
        return usex;
    }
    public void setUage(Long uage)
    {
        this.uage = uage;
    }

    public Long getUage()
    {
        return uage;
    }
    public void setUmarriage(String umarriage)
    {
        this.umarriage = umarriage;
    }

    public String getUmarriage()
    {
        return umarriage;
    }
    public void setUpatientAllergyHistory(String upatientAllergyHistory)
    {
        this.upatientAllergyHistory = upatientAllergyHistory;
    }

    public String getUpatientAllergyHistory()
    {
        return upatientAllergyHistory;
    }
    public void setUpatientMedicalHistory(String upatientMedicalHistory)
    {
        this.upatientMedicalHistory = upatientMedicalHistory;
    }

    public String getUpatientMedicalHistory()
    {
        return upatientMedicalHistory;
    }
    public void setUmainSymptoms(String umainSymptoms)
    {
        this.umainSymptoms = umainSymptoms;
    }

    public String getUmainSymptoms()
    {
        return umainSymptoms;
    }
    public void setClinicalDiagnosis(String clinicalDiagnosis)
    {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public String getClinicalDiagnosis()
    {
        return clinicalDiagnosis;
    }
    public void setUrp(String urp)
    {
        this.urp = urp;
    }

    public String getUrp()
    {
        return urp;
    }
    public void setUcreatedate(Long ucreatedate)
    {
        this.ucreatedate = ucreatedate;
    }

    public Long getUcreatedate()
    {
        return ucreatedate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uvisitDocid", getUvisitDocid())
            .append("userid", getUserid())
            .append("ucompid", getUcompid())
            .append("uemployeeid", getUemployeeid())
            .append("udepartmentid", getUdepartmentid())
            .append("uhospital", getUhospital())
            .append("uvisitingDepartment", getUvisitingDepartment())
            .append("uvisitingDoctor", getUvisitingDoctor())
            .append("umedicalRecordNo", getUmedicalRecordNo())
            .append("upatientName", getUpatientName())
            .append("usex", getUsex())
            .append("uage", getUage())
            .append("umarriage", getUmarriage())
            .append("upatientAllergyHistory", getUpatientAllergyHistory())
            .append("upatientMedicalHistory", getUpatientMedicalHistory())
            .append("umainSymptoms", getUmainSymptoms())
            .append("clinicalDiagnosis", getClinicalDiagnosis())
            .append("urp", getUrp())
            .append("ucreatedate", getUcreatedate())
            .toString();
    }
}
