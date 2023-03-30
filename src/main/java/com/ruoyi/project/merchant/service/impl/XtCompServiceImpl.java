package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.TokenService.AppLoginTokenService;
import com.ruoyi.project.merchant.domain.vo.CompAndDeptSelect;
import com.ruoyi.project.merchant.domain.vo.CompSelect;
import com.ruoyi.project.merchant.domain.vo.MerchantVo;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.domain.query.MerchantQuery;
import com.ruoyi.project.merchant.mapper.XtCompMapper;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.merchant.service.IXtCompService;
import com.ruoyi.project.task.upload.until.StringToURL;
import com.ruoyi.project.user.domain.XtDepartment;
import com.ruoyi.project.user.domain.tree.DeptTreeSelect;
import com.ruoyi.project.user.mapper.XtDepartmentMapper;
import com.ruoyi.project.user.service.IXtDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商家（医院、药店）Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtCompServiceImpl implements IXtCompService
{
    @Autowired
    private XtCompMapper xtCompMapper;


    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @Autowired
    private IXtDepartmentService xtDepartmentService;

    @Autowired
    private XtDepartmentMapper xtDepartmentMapper;

    @Autowired
    private AppLoginTokenService appLoginTokenService;

    /**
     * 查询商家（医院、药店）
     *
     * @param ucompid 商家（医院、药店）主键
     * @return 商家（医院、药店）
     */
    @Override
    public XtComp selectXtCompByUcompid(String ucompid)
    {
        return xtCompMapper.selectXtCompByUcompid(ucompid);
    }

    /**
     * 查询商家（医院、药店）列表
     *
     * @param xtComp   商家（医院、药店）
     * @param pageSize
     * @param pageNum
     * @return 商家（医院、药店）
     */
    @Override
    public PageInfo selectXtCompList(XtComp xtComp, Integer pageSize, Integer pageNum)
    {

        //开始分页
        PageInfo<XtComp> source = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(()->{
            //查询语句
            xtCompMapper.selectXtCompList(xtComp);
        });

        List<XtComp> xtComps = source.getList().stream().collect(Collectors.toList());

        List<MerchantVo> merchantVoList = new ArrayList<>();

        for (int i = 0; i < xtComps.size(); i++) {
            String path = xtComps.get(i).getUdoorFacePic();
            List<String> pics = StringToURL.toURL(path);
            if (pics.size() > 0) {
                xtComps.get(i).setUdoorFacePic(pics.get(0));
            }
            MerchantVo merchantVo = new MerchantVo();
            XtEmployee xtEmployee = xtEmployeeMapper.selectXtEmployeeByUemployeeid(xtComps.get(i).getEmployId());
            merchantVo.setXtEmployee(xtEmployee);
            merchantVo.setXtComp(xtComps.get(i));
            merchantVoList.add(merchantVo);
        }

        //需要转换的对象
        PageInfo<MerchantVo> target = new PageInfo<>();
        BeanUtils.copyProperties(source,target);
        target.setList(merchantVoList);
        return target;
    }

    /**
     * 新增商家（医院、药店）
     *
     * @param xtComp 商家（医院、药店）
     * @return 结果
     */
    @Override
    public int insertXtComp(XtComp xtComp, HttpServletRequest request)
    {
        //判断商家名字是否重复,重复则添加不成功
        if(xtCompMapper.selectAllCompName().contains(xtComp.getUcompname())){
            throw new ServiceException("商家名字重复,请重新输入");
        }

        xtComp.setApplyStatus("0");

        //关联用户表，为商家添加用户id
        AppLoginUser loginMyUser1 = appLoginTokenService.getLoginMyUser(request);
        String userid = loginMyUser1.getUser().getUserid();
        xtComp.setUserid(userid);

        //设置商家id
        String compId = IdUtil.getSnowflakeNextIdStr();
        xtComp.setUcompid(compId);

        //设置注册时间
        xtComp.setCreateTime(new Date());

        //插入商家信息
        xtCompMapper.insertXtCompFromAnnotationDevelopment(xtComp);


        //商家就是一个总的部门，增加商家也是增加一个基本部门
        XtDepartment xtDepartment = new XtDepartment();

        //填入总部门(与商家关联的部门)的基本信息
        xtDepartment.setUdepartmentid(IdUtil.getSnowflakeNextIdStr());
        xtDepartment.setUcompid(compId);
        xtDepartment.setUdepartmentname(xtComp.getUcompname() + "-总部门");
        xtDepartment.setParentId("0");
        xtDepartment.setAncestors("0");
        xtDepartment.setStatus("0");
        xtDepartment.setDelFlag("0");

        //返回增加部门和增加商家的结果
        return xtDepartmentMapper.insertXtDepartment(xtDepartment);
    }

    /**
     * 修改商家（医院、药店）
     *
     * @param xtComp 商家（医院、药店）
     * @return 结果
     */
    @Override
    public int updateXtComp(XtComp xtComp)
    {
        return xtCompMapper.updateXtComp(xtComp);
    }

    /**
     * 批量删除商家（医院、药店）
     *
     * @param ucompids 需要删除的商家（医院、药店）主键
     * @return 结果
     */
    @Override
    public int deleteXtCompByUcompids(String[] ucompids)
    {
        return xtCompMapper.deleteXtCompByUcompids(ucompids);
    }

    /**
     * 删除商家（医院、药店）信息
     *
     * @param ucompid 商家（医院、药店）主键
     * @return 结果
     */
    @Override
    public int deleteXtCompByUcompid(String ucompid)
    {
        return xtCompMapper.deleteXtCompByUcompid(ucompid);
    }

    @Override
    public PageInfo selectAppXtCompList(MerchantQuery merchantQuery) {
        XtComp merchant = new XtComp();
        if (merchantQuery.getPageNum() == null || merchantQuery.getPageSize() == null){
           merchantQuery.setPageNum(1);
           merchantQuery.setPageSize(10);
        }
        //将经纬度转为double
        if (merchantQuery.getLatitude()  == null || merchantQuery.getLongitude() == null){
            //如果没填经纬度,默认使用北京市的经纬度,这样就避免了空值;
            merchantQuery.setLongitude("116.20");
            merchantQuery.setLatitude("39.56");
        }
        Double latitude = Double.valueOf(merchantQuery.getLatitude());
        Double longitude = Double.valueOf(merchantQuery.getLongitude());
        BigDecimal la = BigDecimal.valueOf(latitude);
        BigDecimal lo = BigDecimal.valueOf(longitude);
        merchant.setUlatitude(la);
        merchant.setUlongitude(lo);



        if (merchantQuery.getRegionId() != null){
            //模糊查询该区域以及子区域内的数据,否则将查不到数据
            String regionId = merchantQuery.getRegionId().replaceAll("0+$", "");
            merchant.setUareaid(regionId);
        }

        merchant.setUcompType(merchantQuery.getMerchantType());
        merchant.setUcompname(merchantQuery.getMerchantName());

        //显示商家离我的距离这个功能,需要变更实体,后面再完善;动态调整范围数,也需要更改实体信息;
        //附近10公里范围内的数据
//        PageHelper.startPage(merchantQuery.getPageNum(),merchantQuery.getPageSize());
//        List<XtComp> xtCompList = xtCompMapper.selectXtCompListByQuery(merchant);
//        //需要处理这个list,计算这个经纬度附近的商家;
//        PageInfo list = new PageInfo(xtCompList);


        //开始分页
        PageInfo<XtComp> source = PageHelper.startPage(merchantQuery.getPageNum(), merchantQuery.getPageSize()).doSelectPageInfo(()->{
            //查询语句
            xtCompMapper.selectXtCompListByQuery(merchant);
        });

        List<XtComp> xtComps = source.getList().stream().collect(Collectors.toList());

//        List<MerchantVo> merchantVoList = new ArrayList<>();

        for (int i = 0; i < xtComps.size(); i++) {
            String path = xtComps.get(i).getUdoorFacePic();
            List<String> pics = StringToURL.toURL(path);
            xtComps.get(i).setUdoorFacePic(path);//pics.get(0)
        }

        //需要转换的对象
        PageInfo<XtComp> target = new PageInfo<>();
        BeanUtils.copyProperties(source,target);
        target.setList(xtComps);
        return target;

    }

    @Override
    public List<XtComp> selectXtCompListAll(XtComp xtComp) {
        List<XtComp> xtCompList = xtCompMapper.selectXtCompList(xtComp);
        return xtCompList;
    }

    @Override
    public List<CompSelect> getSelectList() {
        XtComp queryComp = new XtComp();
        queryComp.setApplyStatus("1");
        List<XtComp> xtComps = xtCompMapper.selectXtCompList(queryComp);
        List<CompSelect> result = new ArrayList<>();
        for (XtComp xtComp : xtComps) {
            CompSelect compSelect = new CompSelect();
            compSelect.setValue(xtComp.getUcompid());
            compSelect.setLabel(xtComp.getUcompname());
            result.add(compSelect);
        }
        return result;
    }

    @Override
    public List<CompAndDeptSelect> getCompAndDeptSelectList() {
        XtComp queryComp = new XtComp();
        queryComp.setApplyStatus("1");
        List<XtComp> xtComps = xtCompMapper.selectXtCompList(queryComp);
        List<CompAndDeptSelect> result = new ArrayList<>();
        for (XtComp xtComp : xtComps) {
            CompAndDeptSelect compAndDeptSelect = new CompAndDeptSelect();
            compAndDeptSelect.setValue(xtComp.getUcompid());
            compAndDeptSelect.setLabel(xtComp.getUcompname());
            //构造部门树
            XtDepartment departmentQuery = new XtDepartment();
            departmentQuery.setUcompid(xtComp.getUcompid());
            List<DeptTreeSelect> deptTreeSelectList = xtDepartmentService.getDeptTreeList(departmentQuery);
            compAndDeptSelect.setDepartmentList(deptTreeSelectList);
            result.add(compAndDeptSelect);
        }
        return result;
    }

    @Override
    public Map<String, String> getCompIdAndCompNamet() {
        return xtCompMapper.getCompIdAndCompNamet();
    }

    @Override
    public List<XtComp> selectAllCompInfo() {
        return xtCompMapper.selectAllCompInfo();
    }

    @Override
    public int reviewCompRegistration(String ucompid, String applyStatus) {
        return xtCompMapper.reviewCompRegistrationNoModifyDetails(ucompid,applyStatus,new Date());
    }

    @Override
    public String merchantRegistrationStatus(HttpServletRequest request) {
        AppLoginUser loginMyUser1 = appLoginTokenService.getLoginMyUser(request);
        List<XtComp> xtCompList = xtCompMapper.selectCompByuserid(loginMyUser1.getUserId());
        if(xtCompList.isEmpty()){
            throw new ServiceException("你不是商家，状态应该为空");
        }
        XtComp xtComp = xtCompList.get(0);
        return xtComp.getApplyStatus();
    }

    @Override
    public List<XtComp> selectMyComp() {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();

        List<XtComp> xtCompList = xtCompMapper.selectCompByUserId(userId);
        return xtCompList;
    }


}
