package com.ruoyi.project.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.login.Dto.WebLoginUser;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.user.constant.UserConstants;
import com.ruoyi.project.user.domain.XtDepartment;
import com.ruoyi.project.user.domain.tree.DeptTreeSelect;
import com.ruoyi.project.user.domain.vo.DeptVo;
import com.ruoyi.project.user.mapper.XtDepartmentMapper;
import com.ruoyi.project.user.service.IXtDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门(科室)信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtDepartmentServiceImpl implements IXtDepartmentService
{
    @Autowired
    private XtDepartmentMapper xtDepartmentMapper;

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    /**
     * 查询部门(科室)信息
     *
     * @param udepartmentid 部门(科室)信息主键
     * @return 部门(科室)信息
     */
    @Override
    public XtDepartment selectXtDepartmentByUdepartmentid(String udepartmentid)
    {
        return xtDepartmentMapper.selectXtDepartmentByUdepartmentid(udepartmentid);
    }

    /**
     * 查询部门(科室)信息列表
     *
     * @param xtDepartment 部门(科室)信息
     * @return 部门(科室)信息
     */
    @Override
    public List<XtDepartment> selectXtDepartmentList(XtDepartment xtDepartment)
    {
        WebLoginUser webLoginUser = SecurityUtils.getWebLoginUser();
        if (webLoginUser == null) {
            XtEmployee xtEmployeeByUserId = xtEmployeeMapper.selectXtEmployeeByUserId(SecurityUtils.getAppLoginUser().getUserId());
            xtDepartment.setUcompid(xtEmployeeByUserId.getUcompid());
        }
        return xtDepartmentMapper.selectXtDepartmentList(xtDepartment);
    }

    /**
     * 新增部门(科室)信息
     *
     * @param xtDepartment 部门(科室)信息
     * @return 结果
     */
    @Override
    public int insertXtDepartment(XtDepartment xtDepartment)
    {
        WebLoginUser webLoginUser = SecurityUtils.getWebLoginUser();
        if (webLoginUser != null) {
            //管理员
            String ucompid = xtDepartment.getUcompid();
            if (ucompid == null) {
                throw new RuntimeException("商家id不能为空");
            }
        } else {
            //商家
            XtEmployee xtEmployeeByUserId = xtEmployeeMapper.selectXtEmployeeByUserId(SecurityUtils.getAppLoginUser().getUserId());
            xtDepartment.setUcompid(xtEmployeeByUserId.getUcompid());
        }
        XtDepartment info = xtDepartmentMapper.selectXtDepartmentByUdepartmentid(xtDepartment.getParentId());
        xtDepartment.setAncestors(info.getAncestors()+"," + xtDepartment.getParentId());

        xtDepartment.setUdepartmentid(IdUtil.getSnowflakeNextIdStr());
        return xtDepartmentMapper.insertXtDepartment(xtDepartment);
    }

    /**
     * 修改部门(科室)信息
     *
     * @param xtDepartment 部门(科室)信息
     * @return 结果
     */
    @Override
    public int updateXtDepartment(XtDepartment xtDepartment)
    {

        //部门名称不能重名(新增也要做判断)
        if (!checkDeptNameUnique(xtDepartment)){
            throw new ServiceException("部门名称不能重复");
        }

        //上级部门不能是自己
        if(xtDepartment.getParentId().equals(xtDepartment.getUdepartmentid())){
            throw new ServiceException("父级部门不能是自己.");
        }

        //停用该部门时,如果子部门还有未停用的,则不允许停用
        if(StringUtils.equals(UserConstants.DEPT_DISABLE, xtDepartment.getStatus())){

            int result = xtDepartmentMapper.selectNormalChildrenDeptById(xtDepartment.getUdepartmentid());
            if (result > 0){
                throw new ServiceException("该部门包含未停用的子部门！");
            }
        }

        //保存新旧部门信息
        XtDepartment newParentDept = xtDepartmentMapper.selectXtDepartmentByUdepartmentid(xtDepartment.getParentId());
        XtDepartment oldDept = xtDepartmentMapper.selectXtDepartmentByUdepartmentid(xtDepartment.getUdepartmentid());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept))
        {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getUdepartmentid();
            String oldAncestors = oldDept.getAncestors();
            xtDepartment.setAncestors(newAncestors);
            updateDeptChildren(xtDepartment.getUdepartmentid(), newAncestors, oldAncestors);
        }

        return xtDepartmentMapper.updateXtDepartment(xtDepartment);
    }

    /**
     * 批量删除部门(科室)信息
     *
     * @param udepartmentids 需要删除的部门(科室)信息主键
     * @return 结果
     */
    @Override
    public int deleteXtDepartmentByUdepartmentids(String[] udepartmentids)
    {
        for (int i = 0; i < udepartmentids.length; i++) {
            if(hasChildByDeptId(udepartmentids[i])){
                throw new ServiceException("该部门存在子部门,不允许删除!");
            }

            if(checkDeptExistUser(udepartmentids[i])){
                throw new ServiceException("该部门存在员工,不允许删除!");
            }

        }

        return xtDepartmentMapper.deleteXtDepartmentByUdepartmentids(udepartmentids);
    }

    /**
     * 删除部门(科室)信息信息
     *
     * @param udepartmentid 部门(科室)信息主键
     * @return 结果
     */
    @Override
    public int deleteXtDepartmentByUdepartmentid(String udepartmentid)
    {

        //存在下级部门,不允许删除

        //部门存在员工,不允许删除
        return xtDepartmentMapper.deleteXtDepartmentByUdepartmentid(udepartmentid);
    }

    @Override
    public List<DeptTreeSelect> selectDeptTreeList(XtDepartment dept) {
        WebLoginUser webLoginUser = SecurityUtils.getWebLoginUser();
        if (webLoginUser == null) {
            //商家
            XtEmployee xtEmployeeByUserId = xtEmployeeMapper.selectXtEmployeeByUserId(SecurityUtils.getAppLoginUser().getUserId());
            dept.setUcompid(xtEmployeeByUserId.getUcompid());
        }
        return getDeptTreeList(dept);
    }

    @Override
    public List<DeptVo> getXtDepartmentTree(XtDepartment xtDepartment) {
        WebLoginUser webLoginUser = SecurityUtils.getWebLoginUser();
        if (webLoginUser == null) {
            //商家
            XtEmployee xtEmployeeByUserId = xtEmployeeMapper.selectXtEmployeeByUserId(SecurityUtils.getAppLoginUser().getUserId());
            xtDepartment.setUcompid(xtEmployeeByUserId.getUcompid());
        }
        List<XtDepartment> xtDepartmentList = xtDepartmentMapper.selectXtDepartmentList(xtDepartment);
        List<DeptVo> deptVoList = new ArrayList<>();
        for (XtDepartment department : xtDepartmentList) {
            DeptVo deptVo = new DeptVo();
            BeanUtils.copyProperties(department, deptVo);
            deptVoList.add(deptVo);
        }
        return buildTypeTree(deptVoList);
    }

    private List<DeptVo> buildTypeTree(List<DeptVo> deptVoList) {
        return buildDeptTree(deptVoList);
    }

    public List<DeptTreeSelect> buildDeptTreeSelect(List<DeptVo> depts)
    {
        List<DeptVo> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(DeptTreeSelect::new).collect(Collectors.toList());
    }


    //构建部门树的核心代码
    public List<DeptVo> buildDeptTree(List<DeptVo> depts)
    {
        List<DeptVo> returnList = new ArrayList<DeptVo>();
        List<String> tempList = new ArrayList<>();

        //        List<String> tempList = depts.stream().map(DeptVo::getUdepartmentid).collect(Collectors.toList());
        for (int i = 0; i < depts.size(); i++) {
            String udepartmentid = depts.get(i).getUdepartmentid();
            tempList.add(udepartmentid);
        }

        for (DeptVo dept : depts)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
            {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = depts;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<DeptVo> list, DeptVo t)
    {
        // 得到子节点列表(可以)
        List<DeptVo> childList = getChildList(list, t);
        t.setChildren(childList);
        for (DeptVo tChild : childList)
        {
            //判断子节点是否还有孩子
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<DeptVo> getChildList(List<DeptVo> list, DeptVo t)
    {
        //list:全部;t:其中一个
        List<DeptVo> tlist = new ArrayList<DeptVo>();
        Iterator<DeptVo> it = list.iterator();
        while (it.hasNext())
        {
            DeptVo n = (DeptVo) it.next();
            //判断是否是顶级节点的条件(这个判断条件需要该,每次判断自己是否是顶级节点)
            //将每个子部门去和要判断的部门比较判断,如果属于子部门就加入
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().equals(t.getUdepartmentid()))
            {
                tlist.add(n);
            }
        }
        return tlist;
    }


    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<DeptVo> list, DeptVo t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    private boolean hasChildByDeptId(String deptId)
    {
        int result = xtDepartmentMapper.hasChildByDeptId(deptId);
        return result > 0;
    }

    private boolean checkDeptExistUser(String deptId)
    {
        int result = xtDepartmentMapper.checkDeptExistUser(deptId);
        return result > 0;
    }


    private boolean checkDeptNameUnique(XtDepartment xtDepartment)
    {
        XtDepartment info = xtDepartmentMapper.checkDeptNameUnique(xtDepartment.getUdepartmentid(),xtDepartment.getUdepartmentname(), xtDepartment.getParentId());
        if (info != null){
            return false;
        }
        return true;
    }

    public void updateDeptChildren(String deptId, String newAncestors, String oldAncestors)
    {
        List<XtDepartment> children = xtDepartmentMapper.selectChildrenDeptById(deptId);
        for (XtDepartment child : children)
        {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            xtDepartmentMapper.updateDeptChildren(children);
        }
    }

    @Override
    public List<DeptTreeSelect> getDeptTreeList(XtDepartment dept) {
        List<XtDepartment> depts = xtDepartmentMapper.selectXtDepartmentList(dept);

        List<DeptVo> deptVos = new ArrayList<>();
        for (int i = 0; i < depts.size(); i++) {
            DeptVo deptVo = new DeptVo();
            BeanUtils.copyProperties(depts.get(i),deptVo);
            deptVos.add(deptVo);
        }
        return buildDeptTreeSelect(deptVos);

    }

}
