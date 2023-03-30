package com.ruoyi.project.MQTT;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.myinfo.domain.XtJiankandanan;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by hujun
 * @date 2023-03-28
 */
@RestController
@RequestMapping("/MQTT")
@Api(tags = "先试试看")
public class MQTTController {

    @ApiOperation(value = "收发")
    @GetMapping("/sp")
    public AjaxResult sp() throws MqttException {

        MQTTConnect mqttConnect = new MQTTConnect();
        mqttConnect.setMqttClient("admin", "public", new Callback());
        mqttConnect.sub("com/iot/init");
        mqttConnect.pub("com/iot/init", "Mr.Qu" + (int) (Math.random() * 100000000));

        return  AjaxResult.success("成功");
    }

}
