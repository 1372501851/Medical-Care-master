package com.ruoyi.project.find.service;

import com.ruoyi.project.find.domain.FindModel;
import com.ruoyi.project.find.domain.dto.AddModel;
import com.ruoyi.project.find.domain.query.QueryModel;
import com.ruoyi.project.find.domain.query.QueryWork;
import com.ruoyi.project.find.domain.vo.ModelVo;
import com.ruoyi.project.find.domain.vo.OrderStatus;
import com.ruoyi.project.find.domain.vo.RobOrderVo;
import com.ruoyi.project.find.domain.vo.WorkVo;
import com.ruoyi.project.user.domain.UserInfo;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-11-29
 */
public interface IFindModelService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public FindModel selectFindModelById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param findModel 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FindModel> selectFindModelList(FindModel findModel);

    /**
     * 新增【请填写功能名称】
     *
     * @param addModel@return 结果
     */
    public void insertFindModel(AddModel addModel);

    /**
     * 修改【请填写功能名称】
     *
     * @param findModel 【请填写功能名称】
     * @return 结果
     */
    public int updateFindModel(FindModel findModel);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteFindModelByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteFindModelById(String id);

    List<ModelVo> selectFindModelListByModel(QueryModel queryModel);

    List<RobOrderVo> selectFindModelRobList(QueryModel queryModel);

    void robOrder(String id);

    List<OrderStatus> status(String userId);

    void acceptOrder(String id);

    List<WorkVo> queryWorkList(QueryWork queryWork);

    List<WorkVo> patientsList();
}
