package com.ruoyi.project.merchant.mapper;

import com.ruoyi.project.merchant.domain.XtComp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商家（医院、药店）Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Repository
public interface XtCompMapper
{
    /**
     * 查询商家（医院、药店）
     *
     * @param ucompid 商家（医院、药店）主键
     * @return 商家（医院、药店）
     */
    public XtComp selectXtCompByUcompid(String ucompid);

    /**
     * 查询商家（医院、药店）列表
     *
     * @param xtComp 商家（医院、药店）
     * @return 商家（医院、药店）集合
     */
    public List<XtComp> selectXtCompList(XtComp xtComp);

    /**
     * 新增商家（医院、药店）
     *
     * @param xtComp 商家（医院、药店）
     * @return 结果
     */
    public int insertXtComp(XtComp xtComp);

    /**
     * 修改商家（医院、药店）
     *
     * @param xtComp 商家（医院、药店）
     * @return 结果
     */
    public int updateXtComp(XtComp xtComp);

    /**
     * 删除商家（医院、药店）
     *
     * @param ucompid 商家（医院、药店）主键
     * @return 结果
     */
    public int deleteXtCompByUcompid(String ucompid);

    /**
     * 批量删除商家（医院、药店）
     *
     * @param ucompids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtCompByUcompids(String[] ucompids);

    int updateXtCompApplyStatus(String compnyId, String status);

    List<XtComp> selectXtCompListByQuery(XtComp merchant);

    List<XtComp> selectByid(String applyStatus);

    /**
     * 拿到所有商家的对应的名字和Id
     * @return{
     */
    @Select("select ucompname , ucompid from xt_comp")
    Map<String,String> getCompIdAndCompNamet();

    @Select("SELECT * FROM xt_comp")
    List<XtComp> selectAllCompInfo();

    /**
     * 插入商家信息
     * @param xtComp
     * @return
     */
    @Insert("insert into xt_comp (ucompid,userid,ucompname,uparentid,branch_comp_name,umanage_model,chain_organization_name,ucomp_type,uareaid,ucommunity,uaddress,address_detail,ulatitude,ulongitude,ulegal_person,ulegal_idcard,ulegal_mobile,utel,udoor_face_pic,uidcardzpic,uidcardfpic,ubusiness_license,ubusiness_license_pic,uindustry_license_pic,upharmacist_license_pic,umedical_license_pic,ufire_safety_pic,urelevant_certificates_pic,udiploma_pic,uprofessional_title_pic,upractice_license_pic,uother_certificates_pic,ucreatedate,apply_status,uapproval_time,create_time,update_time,employ_id,score,food_hygiene_license_pic,industry_association,spare_one,spare_two,spare_three,office_space,del_flag,ucustomerid,set_up_time,register_amount) " +
                           "values (#{ucompid},#{userid},#{ucompname},#{uparentid},#{branchCompName},#{umanageModel},#{chainOrganizationName},#{ucompType},#{uareaid},#{ucommunity},#{uaddress},#{addressDetail},#{ulatitude},#{ulongitude},#{ulegalPerson},#{ulegalIdcard},#{ulegalMobile},#{utel},#{udoorFacePic},#{uidcardzpic},#{uidcardfpic},#{ubusinessLicense},#{ubusinessLicensePic},#{uindustryLicensePic},#{upharmacistLicensePic},#{umedicalLicensePic},#{ufireSafetyPic},#{urelevantCertificatesPic},#{udiplomaPic},#{uprofessionalTitlePic},#{upracticeLicensePic},#{uotherCertificatesPic},#{ucreatedate},#{applyStatus},#{uapprovalTime},#{createTime},#{updateTime},#{employId},#{score},#{foodHygieneLicensePic},#{industryAssociation},#{spareOne},#{spareTwo},#{spareThree},#{officeSpace},#{delFlag},#{ucustomerid},#{setUpTime},#{registerAmount})")
    int insertXtCompFromAnnotationDevelopment(XtComp xtComp);

    /**
     * 审核商家注册是否通过，可以修改版（使用插入语句重新插入数据，因为主键相同所有可以数据覆盖达到修改的目的）
     * @param xtComp 商家
     * @return
     */
    @Insert("insert into xt_comp (ucompid,userid,ucompname,uparentid,branch_comp_name,umanage_model,chain_organization_name,ucomp_type,uareaid,ucommunity,uaddress,address_detail,ulatitude,ulongitude,ulegal_person,ulegal_idcard,ulegal_mobile,utel,udoor_face_pic,uidcardzpic,uidcardfpic,ubusiness_license,ubusiness_license_pic,uindustry_license_pic,upharmacist_license_pic,umedical_license_pic,ufire_safety_pic,urelevant_certificates_pic,udiploma_pic,uprofessional_title_pic,upractice_license_pic,uother_certificates_pic,ucreatedate,apply_status,uapproval_time,create_time,update_time,employ_id,score,food_hygiene_license_pic,industry_association,spare_one,spare_two,spare_three,office_space,del_flag,ucustomerid,set_up_time,register_amount) " +
            "values (#{ucompid},#{userid},#{ucompname},#{uparentid},#{branchCompName},#{umanageModel},#{chainOrganizationName},#{ucompType},#{uareaid},#{ucommunity},#{uaddress},#{addressDetail},#{ulatitude},#{ulongitude},#{ulegalPerson},#{ulegalIdcard},#{ulegalMobile},#{utel},#{udoorFacePic},#{uidcardzpic},#{uidcardfpic},#{ubusinessLicense},#{ubusinessLicensePic},#{uindustryLicensePic},#{upharmacistLicensePic},#{umedicalLicensePic},#{ufireSafetyPic},#{urelevantCertificatesPic},#{udiplomaPic},#{uprofessionalTitlePic},#{upracticeLicensePic},#{uotherCertificatesPic},#{ucreatedate},#{applyStatus},#{uapprovalTime},#{createTime},#{updateTime},#{employId},#{score},#{foodHygieneLicensePic},#{industryAssociation},#{spareOne},#{spareTwo},#{spareThree},#{officeSpace},#{delFlag},#{ucustomerid},#{setUpTime},#{registerAmount})")
    int reviewCompRegistration(XtComp xtComp);

    /**
     * 审核商家注册是否通过，不可修改版
     */
    @Update("update xt_comp set apply_status=#{status},create_time = #{createTime} where ucompid = #{id};")
    int reviewCompRegistrationNoModifyDetails(String id,String status, Date createTime);

    /**
     * 查询所有商家名字
     * @return
     */
    @Select("SELECT ucompname FROM xt_comp")
    List<String> selectAllCompName();

    @Select("SELECT * FROM xt_comp WHERE userId = #{userId}")
    List<XtComp> selectCompByUserId(String userId);

    /**
     * 模糊查询商家名字
     * @param ucompname
     * @return
     */
    @Select("select * from xt_comp where ucompname like concat('%',#{ucompname},'%')")
    List<XtComp> fuzzyQueryCompName(String ucompname);

    /**
     * 通过用户id查找商家
     * @param userid
     * @return
     */
    @Select("select * FROM xt_comp where userid = #{userid}")
    List<XtComp> selectCompByuserid(String userid);

    @Select("SELECT ucompname FROM xt_comp WHERE ucompid = #{id}")
    XtComp selectNameById(String id);

    @Select("SELECT ucompname FROM xt_comp WHERE ucompid = #{id} ")
    String selectCompnmae(String id);
}
