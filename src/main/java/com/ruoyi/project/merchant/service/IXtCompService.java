package com.ruoyi.project.merchant.service;

import com.github.pagehelper.PageInfo;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.query.MerchantQuery;
import com.ruoyi.project.merchant.domain.vo.CompAndDeptSelect;
import com.ruoyi.project.merchant.domain.vo.CompSelect;
import org.apache.ibatis.annotations.Select;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 商家（医院、药店）Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtCompService
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
     * @param xtComp   商家（医院、药店）
     * @param pageSize
     * @param pageNum
     * @return 商家（医院、药店）集合
     */
    public PageInfo selectXtCompList(XtComp xtComp, Integer pageSize, Integer pageNum);

    /**
     * 新增商家（医院、药店）,商家注册
     * @param xtComp 商家（医院、药店）
     * @return 结果
     */
    int insertXtComp(XtComp xtComp, HttpServletRequest request);

    /**
     * 修改商家（医院、药店）
     *
     * @param xtComp 商家（医院、药店）
     * @return 结果
     */
    public int updateXtComp(XtComp xtComp);

    /**
     * 批量删除商家（医院、药店）
     *
     * @param ucompids 需要删除的商家（医院、药店）主键集合
     * @return 结果
     */
    public int deleteXtCompByUcompids(String[] ucompids);

    /**
     * 删除商家（医院、药店）信息
     *
     * @param ucompid 商家（医院、药店）主键
     * @return 结果
     */
    public int deleteXtCompByUcompid(String ucompid);

    PageInfo selectAppXtCompList(MerchantQuery merchantQuery);

    List<XtComp> selectXtCompListAll(XtComp xtComp);

    List<CompSelect> getSelectList();

    List<CompAndDeptSelect> getCompAndDeptSelectList();

    /**
     * 拿到所有商家的对应的名字和Id
     * @return
     */
    Map<String,String> getCompIdAndCompNamet();

    List<XtComp> selectAllCompInfo();

    /**
     * 审核商家注册是否通过
     * @param applyStatus 注册审核状态 0：注册审核中，1：注册审核通过，2：注册审核不通过
     * @return
     */
    int reviewCompRegistration(String ucompid,String applyStatus);

    /**
     * 小刘专用,token判断商家注册状态
     * @param request
     * @return
     */
    String merchantRegistrationStatus(HttpServletRequest request);

    List<XtComp> selectMyComp();
}
