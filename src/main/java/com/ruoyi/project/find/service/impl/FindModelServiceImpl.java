package com.ruoyi.project.find.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.find.domain.FindModel;
import com.ruoyi.project.find.domain.dto.AddModel;
import com.ruoyi.project.find.domain.query.QueryModel;
import com.ruoyi.project.find.domain.query.QueryWork;
import com.ruoyi.project.find.domain.vo.ModelVo;
import com.ruoyi.project.find.domain.vo.OrderStatus;
import com.ruoyi.project.find.domain.vo.RobOrderVo;
import com.ruoyi.project.find.domain.vo.WorkVo;
import com.ruoyi.project.find.enums.IdentityEnum;
import com.ruoyi.project.find.mapper.FindModelMapper;
import com.ruoyi.project.find.service.IFindModelService;
import com.ruoyi.project.find.util.ModelConstant;
import com.ruoyi.project.im.domain.XtGroup;
import com.ruoyi.project.im.domain.XtUserToGroup;
import com.ruoyi.project.im.mapper.XtGroupMapper;
import com.ruoyi.project.im.mapper.XtUserToGroupMapper;
import com.ruoyi.project.im.tio.TioServerConfig;
import com.ruoyi.project.im.tio.WsOnlineContext;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtCompMapper;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.task.upload.until.StringToURL;
import com.ruoyi.project.user.domain.UserInfo;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.mapper.UserInfoMapper;
import com.ruoyi.project.user.mapper.XtUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.websocket.common.WsResponse;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-29
 */
@Service
public class FindModelServiceImpl implements IFindModelService
{
    @Autowired
    private FindModelMapper findModelMapper;

    @Autowired
    private XtUserMapper userMapper;


    @Autowired
    private XtCompMapper compMapper;


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private XtGroupMapper groupMapper;

    @Autowired
    private XtUserToGroupMapper userToGroupMapper;

    @Autowired
    private XtEmployeeMapper employeeMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public FindModel selectFindModelById(String id)
    {
        return findModelMapper.selectFindModelById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param findModel 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FindModel> selectFindModelList(FindModel findModel)
    {
        return findModelMapper.selectFindModelList(findModel);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param addModel@return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertFindModel(AddModel addModel)
    {
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();

        FindModel findModel = new FindModel();
        findModel.setId(IdUtil.getSnowflakeNextIdStr());
        findModel.setCreateTime(new Date());
        findModel.setServiceType(addModel.getServiceType());
        findModel.setStatus(ModelConstant.NOT_ORDER);
        findModel.setUserId(userid);
        findModel.setCompId(addModel.getCompId());
        findModel.setMessage(addModel.getMessage());

        UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(userid);
        findModel.setLatitude(userInfo.getLatitude());
        findModel.setLongitude(userInfo.getLongitude());

        //这里需要改为可以选择多个医生/护工/......


        //保存监护人id
        if (addModel.getGuardianIds().length != 0){
            String  collect = Arrays.stream(addModel.getGuardianIds()).collect(Collectors.joining(","));
            findModel.setGuardianIds(collect);
        }


        //保存服务者的id
        if (addModel.getServiceId().length == 0){
            //未指定护工,则该订单可抢
            findModel.setRobStatus(ModelConstant.IS_ROB);
            findModelMapper.insertFindModel(findModel);
        } else {
            //指定了护工
            for (int i = 0; i < addModel.getServiceId().length; i++) {
                //一次保存给每位医生的订单
                findModel.setServerId(addModel.getServiceId()[i]);
                //订单不可抢
                findModel.setRobStatus(ModelConstant.NOT_ROB);

                //创建群聊
                String groupId = createGroup(userid, findModel.getGuardianIds(), findModel.getServerId());
                findModel.setChatId(groupId);
                findModelMapper.insertFindModel(findModel);
            }
        }


    }

    /**
     * 修改【请填写功能名称】
     *
     * @param findModel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFindModel(FindModel findModel)
    {
        return findModelMapper.updateFindModel(findModel);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteFindModelByIds(String[] ids)
    {
        return findModelMapper.deleteFindModelByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteFindModelById(String id)
    {
        return findModelMapper.deleteFindModelById(id);
    }

    @Override
    public List<ModelVo> selectFindModelListByModel(QueryModel queryModel) {
        FindModel findModel = new FindModel();
        BeanUtils.copyProperties(queryModel,findModel);
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        findModel.setUserId(userid);
        findModel.setRobStatus(ModelConstant.NOT_ROB);
        List<FindModel> findModels = findModelMapper.selectFindModelList(findModel);
        List<ModelVo> modelVos = new ArrayList<>();

        if (!CollectionUtils.isEmpty(findModels)){
            for (int i = 0; i < findModels.size(); i++) {
                ModelVo modelVo = new ModelVo();
                BeanUtils.copyProperties(findModels.get(i),modelVo);
                //查询服务者信息
                if (modelVo.getServerId() != null){
                    XtUser xtUser = userMapper.selectXtUserByUserid(modelVo.getServerId());
                    modelVo.setServiceName(xtUser.getUsername());


                    //处理订单状态
                    if (modelVo.getStatus().equals(ModelConstant.NOT_ORDER)){
                        modelVo.setStatusName("待接单");
                    } else if (modelVo.getStatus().equals(ModelConstant.DENY_ORDER)) {
                        modelVo.setStatusName("已拒绝");
                    } else if (modelVo.getStatus().equals(ModelConstant.IS_ORDER)) {
                        modelVo.setStatusName("已接单");
                    } else if (modelVo.getStatus().equals(ModelConstant.IS_PAY)) {
                        modelVo.setStatusName("已付款");
                    }else if (modelVo.getStatus().equals(ModelConstant.NOT_PAY)) {
                        modelVo.setStatusName("待付款");
                    }else if (modelVo.getStatus().equals(ModelConstant.DENY_PAY)) {
                        modelVo.setStatusName("已退款");
                    }

                    List<String> url = StringToURL.toURL(ModelConstant.ORDER_AVATAR);
                    modelVo.setAvatar(url.get(0));

                    modelVos.add(modelVo);
                }


            }
        }


        return modelVos;
    }

    @Override
    public List<RobOrderVo> selectFindModelRobList(QueryModel queryModel) {

        FindModel findModel = new FindModel();
        BeanUtils.copyProperties(queryModel,findModel);
        findModel.setRobStatus(ModelConstant.IS_ROB);
        List<FindModel> findModels = findModelMapper.selectFindRobList(findModel);
        List<RobOrderVo> robOrderVos = new ArrayList<>();

        if (!CollectionUtils.isEmpty(findModels)){
            for (int i = 0; i < findModels.size(); i++) {
               RobOrderVo robOrderVo = new RobOrderVo();

               //查询医院名称
                FindModel model = findModels.get(i);

                //查询病人床位信息
                UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(model.getUserId());
                robOrderVo.setId(model.getId());
                robOrderVo.setMessage("寻找护工");
                robOrderVo.setDeptName(userInfo.getDept()+ "/" + "/" + userInfo.getBed());
                robOrderVo.setDate(model.getCreateTime());
                robOrderVo.setHospitalName(userInfo.getHospital());

                List<String> url = StringToURL.toURL(ModelConstant.ORDER_AVATAR);
                robOrderVo.setAvatar(ModelConstant.ORDER_AVATAR);
                robOrderVos.add(robOrderVo);
            }

        }
        return  robOrderVos;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void robOrder(String id) {
        //需要加锁,否则会出问题
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        FindModel findModel = findModelMapper.selectFindModelById(id);

        if (findModel.getRobStatus().equals(ModelConstant.NOT_ROB)){
            throw new ServiceException("该订单已被抢");
        }
        //更新该订单的服务者id
        findModel.setServerId(userid);

        //更新订单的可抢状态
        findModel.setRobStatus(ModelConstant.NOT_ROB);

        //更新订单状态
        findModel.setStatus(ModelConstant.IS_ORDER);


        //抢单成功后,需要创建群聊;(护工接受订单后也是需要创建群聊)

        String groupId = createGroup(userid, findModel.getGuardianIds(), findModel.getServerId());

        findModel.setChatId(groupId);

        int count = findModelMapper.updateFindModel(findModel);

        if (count == 0){
            throw new ServiceException("抢单失败");
        }

        //每个用户都有一个上下文,根据WsOnlineContext获取该用户的上下文环境(由服务器主动发消息给用户)
        ChannelContext channelContext = WsOnlineContext.getChannelContextByUser(findModel.getUserId());
        WsResponse wsResponse = WsResponse.fromText("抢单成功", TioServerConfig.CHARSET);
        Tio.sendToUser(channelContext.tioConfig, findModel.getUserId(), wsResponse);


    }


    private String  createGroup(String userId,String guardianIds,String serviceId){
        //创建群聊,生成群id(不用发送默认信息了)
        XtGroup group = new XtGroup();
        group.setUgroupid(IdUtil.getSnowflakeNextIdStr());

        List<String> guardianIdList = new ArrayList<>();
        //获取订单中的监护人id列表,获取服务者id;

        if (guardianIds != null){
           guardianIdList = Arrays.asList(guardianIds.split(","));
            if (!CollectionUtils.isEmpty(guardianIdList)) {
                for (int j = 0; j < guardianIdList.size(); j++) {
                    XtUserToGroup userToGroup = new XtUserToGroup();
                    userToGroup.setUsergroupid(IdUtil.getSnowflakeNextIdStr());
                    userToGroup.setCreatTime(new Date());
                    userToGroup.setUserid(guardianIdList.get(j));
                    userToGroup.setUgroupid(group.getUgroupid());
                    userToGroupMapper.insertXtUserToGroup(userToGroup);
                }
            }

        }

        //把医生拉入群中
        XtUserToGroup userToGroup = new XtUserToGroup();
        userToGroup.setUsergroupid(IdUtil.getSnowflakeNextIdStr());
        userToGroup.setCreatTime(new Date());
        userToGroup.setUserid(serviceId);
        userToGroup.setUgroupid(group.getUgroupid());
        userToGroupMapper.insertXtUserToGroup(userToGroup);

        //根据医生信息,查找商家信息
        XtEmployee employee = employeeMapper.selectXtEmployeeByUserId(serviceId);
        String compId = employee.getUcompid();
        //医院信息
        XtComp xtComp = compMapper.selectXtCompByUcompid(compId);

        group.setUcompid(xtComp.getUcompid());

        group.setUdepartmentid(employee.getUdepartmentid());


        //把自己拉入群中
        XtUserToGroup userToGroup2 = new XtUserToGroup();
        userToGroup2.setUsergroupid(IdUtil.getSnowflakeNextIdStr());
        userToGroup2.setCreatTime(new Date());
        userToGroup2.setUserid(userId);
        userToGroup2.setUgroupid(group.getUgroupid());
        userToGroupMapper.insertXtUserToGroup(userToGroup2);


        XtUser my = userMapper.selectXtUserByUserid(userId);
        XtUser service = userMapper.selectXtUserByUserid(serviceId);

        //群聊名称(多人的姓名)
        group.setUgroupname(my.getUsername() + "," + service.getUsername());

        List<String> urls = StringToURL.toURL(ModelConstant.GROUP_AVATAR);

        group.setUgroupavatar(urls.get(0));
        group.setCreateTime(new Date());

        groupMapper.insertXtGroup(group);

        return group.getUgroupid();

    }

    @Override
    public List<OrderStatus> status(String userId) {
        FindModel findModel = new FindModel();
        findModel.setUserId(userId);
        List<FindModel> findModels = findModelMapper.selectFindModelList(findModel);

        List<OrderStatus> orderStatuses = new ArrayList<>();

        if (!CollectionUtils.isEmpty(findModels)){
            for (int i = 0; i < findModels.size(); i++) {
                OrderStatus orderStatus = new OrderStatus();
                //查询医院名称
                FindModel model = findModels.get(i);
                //查询病人床位信息
                UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(model.getUserId());
                orderStatus.setDeptName(userInfo.getDept()+ "/" + "/" + userInfo.getBed());
                orderStatus.setDate(model.getCreateTime());
                orderStatus.setHospitalName(userInfo.getHospital());
                orderStatus.setId(model.getId());
                orderStatus.setStatus(model.getStatus());

                String status = model.getStatus();
                if (status.equals(ModelConstant.NOT_ORDER)){
                    orderStatus.setStatusName("待接单");
                } else if (status.equals(ModelConstant.DENY_ORDER)) {
                    orderStatus.setStatusName("已拒绝");
                } else if (status.equals(ModelConstant.IS_ORDER)) {
                    orderStatus.setStatusName("已接单");
                }

                //处理头像问题
                List<String> url = StringToURL.toURL(ModelConstant.ORDER_AVATAR);
                orderStatus.setAvatar(ModelConstant.ORDER_AVATAR);

                orderStatuses.add(orderStatus);
            }

        }
        return  orderStatuses;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void acceptOrder(String id) {

        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        FindModel findModel = findModelMapper.selectFindModelById(id);
        //更新订单状态
        findModel.setStatus(ModelConstant.IS_ORDER);

        int count = findModelMapper.updateFindModel(findModel);
        if (count == 0){
            throw new ServiceException("接单失败");
        }

        //接单后,需要通过socket给发布者发送消息,刷新页面

        //每个用户都有一个上下文,根据WsOnlineContext获取该用户的上下文环境(由服务器主动发消息给用户)
        ChannelContext channelContext = WsOnlineContext.getChannelContextByUser(findModel.getUserId());
        WsResponse wsResponse = WsResponse.fromText("接单成功", TioServerConfig.CHARSET);
        Tio.sendToUser(channelContext.tioConfig, findModel.getUserId(), wsResponse);

    }

    @Override
    public List<WorkVo> queryWorkList(QueryWork queryWork) {

        UserInfo userInfo = new UserInfo();

        //将经纬度转为double
        if (queryWork.getLatitude()  == null || queryWork.getLongitude() == null){
            //如果没填经纬度,默认使用北京市的经纬度,这样就避免了空值;
            queryWork.setLongitude("116.20");
            queryWork.setLatitude("39.56");
        }
        Double latitude = Double.valueOf(queryWork.getLatitude());
        Double longitude = Double.valueOf(queryWork.getLongitude());
        BigDecimal la = BigDecimal.valueOf(latitude);
        BigDecimal lo = BigDecimal.valueOf(longitude);
        BeanUtils.copyProperties(queryWork,userInfo);

        userInfo.setLatitude(la);
        userInfo.setLongitude(lo);
        List<UserInfo> userInfos = userInfoMapper.selectUserInfoList(userInfo);
        List<WorkVo> workVos = new ArrayList<>();

        if (!CollectionUtils.isEmpty(userInfos)){
            for (int i = 0; i < userInfos.size(); i++) {
                WorkVo workVo = new WorkVo();
                UserInfo info = userInfos.get(i);
                XtUser xtUser = userMapper.selectXtUserByUserid(info.getUserId());
                BeanUtils.copyProperties(info,workVo);
                workVo.setUserid(xtUser.getUserid());
                //身份枚举类的使用
                workVo.setUserTypeName(IdentityEnum.identityEnumMap.get(info.getUserType()));
                //路劲转url工具的使用
                List<String> url = StringToURL.toURL(xtUser.getAvatar());
                workVo.setAvatar(url.get(0));
                workVos.add(workVo);
            }
        }

        return workVos;

    }

    @Override
    public List<WorkVo> patientsList() {
        String userId = SecurityUtils.getAppLoginUser().getUserId();
        FindModel findModel = new FindModel();
        findModel.setServerId(userId);
        List<FindModel> findModels = findModelMapper.selectFindModelList(findModel);
        List<String> userIds = findModels.stream().map(FindModel::getUserId).collect(Collectors.toList());
        List<WorkVo> workVos = new ArrayList<>();

        for (int i = 0; i < userIds.size(); i++) {
            UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(userIds.get(i));
            XtUser xtUser = userMapper.selectXtUserByUserid(userInfo.getUserId());
            WorkVo workVo = new WorkVo();
            BeanUtils.copyProperties(userInfo,workVo);
            workVo.setUserid(xtUser.getUserid());
            //身份枚举类的使用
            workVo.setUserTypeName(IdentityEnum.identityEnumMap.get(userInfo.getUserType()));
            //路劲转url工具的使用
            List<String> url = StringToURL.toURL(xtUser.getAvatar());
            workVo.setAvatar(url.get(0));
            workVos.add(workVo);
        }

        return workVos;
    }


}
