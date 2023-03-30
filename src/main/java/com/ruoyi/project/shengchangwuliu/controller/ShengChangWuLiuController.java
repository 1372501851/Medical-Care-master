package com.ruoyi.project.shengchangwuliu.controller;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.merchant.domain.XtOrder;
import com.ruoyi.project.merchant.domain.XtProduct;
import com.ruoyi.project.merchant.mapper.XtCompMapper;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.merchant.mapper.XtProductMapper;
import com.ruoyi.project.merchant.service.IXtOrderService;
import com.ruoyi.project.shengchangwuliu.domain.DaKaOfLiuCheng;
import com.ruoyi.project.shengchangwuliu.domain.Liuchengbianzhi;
import com.ruoyi.project.shengchangwuliu.domain.vo.DaKaOfLiuChengVo;
import com.ruoyi.project.shengchangwuliu.domain.vo.LiuchengbianzhiVo;
import com.ruoyi.project.shengchangwuliu.mapper.DaKaOfLiuChengMapper;
import com.ruoyi.project.shengchangwuliu.mapper.LiuchengbianzhiMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author by hujun
 * @date 2023-02-25
 */
@RestController
@RequestMapping("/ShengChangWuLiu")
@Api(tags ="生产物流模块")
public class ShengChangWuLiuController {

    @Autowired
    private LiuchengbianzhiMapper liuchengbianzhiMapper;

    @Autowired
    private IXtOrderService iXtOrderService;

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @Autowired
    private XtProductMapper xtProductMapper;

    @Autowired
    private DaKaOfLiuChengMapper daKaOfLiuChengMapper;

    @ApiOperation(value = "增加流程编制")
    @PostMapping("/addLiucheng")
    public AjaxResult addLiucheng(@RequestBody LiuchengbianzhiVo liuchengbianzhiVo)
    {
        String join = String.join("%%%%%", liuchengbianzhiVo.getBuzhoulist());

        Liuchengbianzhi liuchengbianzhi = new Liuchengbianzhi();
        BeanUtils.copyProperties(liuchengbianzhiVo , liuchengbianzhi);

        liuchengbianzhi.setId(IdUtil.getSnowflakeNextIdStr());
        liuchengbianzhi.setUtime(new Date());

        liuchengbianzhi.setBuzhoulist(join);

        return AjaxResult.success(liuchengbianzhiMapper.addLiucheng(liuchengbianzhi));
    }


    @ApiOperation(value = "模糊查找流程编制")
    @GetMapping("/likeseleLiucheng/{name}")
    public AjaxResult likeseleLiucheng(@PathVariable String name)
    {
        return AjaxResult.success(liuchengbianzhiMapper.likeseleLiucheng("%"+name+"%"));
    }

    @ApiOperation(value = "查看流程编制详细信息")
    @GetMapping("/seleLiuchengById/{id}")
    public AjaxResult seleLiuchengById(@PathVariable String id)
    {
        Liuchengbianzhi data = liuchengbianzhiMapper.seleLiuchengById(id);
        LiuchengbianzhiVo liuchengbianzhiVo = new LiuchengbianzhiVo();
        BeanUtils.copyProperties(data , liuchengbianzhiVo);
        String[] split = data.getBuzhoulist().split("%%%%%");
        liuchengbianzhiVo.setBuzhoulist(Arrays.asList(split));

        return AjaxResult.success(liuchengbianzhiVo);
    }

    @ApiOperation(value = "查找所有流程编制")
    @GetMapping("/seleAllLiucheng")
    public AjaxResult seleAllLiucheng()
    {
        List<Liuchengbianzhi> data = liuchengbianzhiMapper.seleAllLiucheng();
        return AjaxResult.success(
                data);
    }

    @ApiOperation(value = "修改流程编制")
    @PutMapping("/updateLiucheng")
    public AjaxResult updateLiucheng(@RequestBody LiuchengbianzhiVo liuchengbianzhiVo)
    {
        Liuchengbianzhi liuchengbianzhi = new Liuchengbianzhi();
        BeanUtils.copyProperties(liuchengbianzhiVo , liuchengbianzhi);
        String join = String.join("%%%%%",liuchengbianzhiVo.getBuzhoulist());
        liuchengbianzhi.setBuzhoulist(join);

        return AjaxResult.success(liuchengbianzhiMapper.updateLiucheng(liuchengbianzhi));
    }

    @ApiOperation(value = "根据商品id查看本员工所属的公司的该商品的已经支付的订单,记得用123的账号登录测试")
    @GetMapping("/seleOrderByProductId")
    public AjaxResult seleOrderByProductId(String productId)
    {
        XtOrder order = new XtOrder();
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        order.setUcompid(xtEmployeeMapper.selectXtEmployeeByUserId(userid).getUcompid());
        order.setUstatus("1");
        order.setProductId(productId);
        List<XtOrder> xtOrders = iXtOrderService.selectXtOrderList(order);
        return AjaxResult.success(xtOrders);
    }

    @ApiOperation(value = "获取订单中的商品")
    @GetMapping("/selectProductofOrder")
    public AjaxResult selectProductofOrder()
    {
        XtOrder order = new XtOrder();
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        order.setUcompid(xtEmployeeMapper.selectXtEmployeeByUserId(userid).getUcompid());
        order.setUstatus("1");
        List<XtOrder> xtOrders = iXtOrderService.selectXtOrderList(order);
        HashSet<String> strings = new HashSet<>();
        ArrayList<XtProduct> xtProducts = new ArrayList<>();
        for (XtOrder x:
                xtOrders) {
            String productId1 = x.getProductId();
            strings.add(productId1);
        }
        for (String x:
             strings) {
            XtProduct product = xtProductMapper.selectAll(x);
            xtProducts.add(product);
        }
        return AjaxResult.success(xtProducts);
    }

    @ApiOperation(value = "增加或者查找流程步骤打卡")
    @GetMapping("/seleOrAddAllLiucheng")
    public AjaxResult seleOrAddAllLiucheng(String orderId , String liuChenId)
    {
        if(daKaOfLiuChengMapper.selectDaKaOfLiuCheng(orderId , liuChenId).isEmpty()){
            DaKaOfLiuCheng daKaOfLiuCheng = new DaKaOfLiuCheng();

            ArrayList<String> status = new ArrayList<>();
            ArrayList<String> peoplename = new ArrayList<>();
            ArrayList<String> utime = new ArrayList<>();

            String buzhoulist = liuchengbianzhiMapper.seleLiuchengById(liuChenId).getBuzhoulist();
            String[] split = buzhoulist.split("%%%%%");
            for (String x:
                    split) {
                status.add("0");
                peoplename.add("0");
                utime.add("0");
            }

            String join = String.join("%%%%%", status);
            String join1 = String.join("%%%%%", peoplename );
            String join2 = String.join("%%%%%", utime);


            daKaOfLiuCheng.setLiuchengid(liuChenId);
            daKaOfLiuCheng.setOrderid(orderId);
            daKaOfLiuCheng.setId(IdUtil.getSnowflakeNextIdStr());
            daKaOfLiuCheng.setStatus(join);
            daKaOfLiuCheng.setPeoplename(join1);
            daKaOfLiuCheng.setUtime(join2);
            daKaOfLiuChengMapper.addDaKa(daKaOfLiuCheng);


            List<DaKaOfLiuCheng> data = daKaOfLiuChengMapper.selectDaKaOfLiuCheng(orderId, liuChenId);
            DaKaOfLiuCheng daKaOfLiuCheng1 = data.get(0);
            DaKaOfLiuChengVo daKaOfLiuChengVo = new DaKaOfLiuChengVo();
            BeanUtils.copyProperties(daKaOfLiuCheng1 , daKaOfLiuChengVo);


            String[] split1 = daKaOfLiuCheng1.getStatus().split("%%%%%");
            String[] split2 = daKaOfLiuCheng1.getPeoplename().split("%%%%%");
            String[] split3 = daKaOfLiuCheng1.getUtime().split("%%%%%");


            List<String> strings = Arrays.asList(split1);
            List<String> strings1 = Arrays.asList(split2);
            List<String> strings2 = Arrays.asList(split3);


            daKaOfLiuChengVo.setStatus(strings);
            daKaOfLiuChengVo.setUtime(strings2);
            daKaOfLiuChengVo.setPeoplename(strings1);

            return AjaxResult.success(daKaOfLiuChengVo);

        }else {
            List<DaKaOfLiuCheng> data = daKaOfLiuChengMapper.selectDaKaOfLiuCheng(orderId, liuChenId);
            DaKaOfLiuCheng daKaOfLiuCheng1 = data.get(0);
            DaKaOfLiuChengVo daKaOfLiuChengVo = new DaKaOfLiuChengVo();
            BeanUtils.copyProperties(daKaOfLiuCheng1 , daKaOfLiuChengVo);


            String[] split1 = daKaOfLiuCheng1.getStatus().split("%%%%%");
            String[] split2 = daKaOfLiuCheng1.getPeoplename().split("%%%%%");
            String[] split3 = daKaOfLiuCheng1.getUtime().split("%%%%%");


            List<String> strings = Arrays.asList(split1);
            List<String> strings1 = Arrays.asList(split2);
            List<String> strings2 = Arrays.asList(split3);


            daKaOfLiuChengVo.setStatus(strings);
            daKaOfLiuChengVo.setUtime(strings2);
            daKaOfLiuChengVo.setPeoplename(strings1);

            return AjaxResult.success(daKaOfLiuChengVo);
        }
    }

    @ApiOperation(value = "修改打卡状态")
    @PutMapping("/updateDakaStatus")
    public AjaxResult updateDakaStatus(@RequestBody DaKaOfLiuChengVo daKaOfLiuChengVo)
    {
        DaKaOfLiuCheng daKaOfLiuCheng = new DaKaOfLiuCheng();
        BeanUtils.copyProperties(daKaOfLiuChengVo,daKaOfLiuCheng);

        String join = String.join("%%%%%", daKaOfLiuChengVo.getStatus());
        String join1 = String.join("%%%%%", daKaOfLiuChengVo.getPeoplename());
        String join2 = String.join("%%%%%", daKaOfLiuChengVo.getUtime());

        daKaOfLiuCheng.setStatus(join);
        daKaOfLiuCheng.setPeoplename(join1);
        daKaOfLiuCheng.setUtime(join2);

        return AjaxResult.success(daKaOfLiuChengMapper.updateDaKa(daKaOfLiuCheng));
    }

}
