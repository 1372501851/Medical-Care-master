package com.ruoyi.project.MQTT;


import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;



/**
 * 项目启动 监听主题
 *
 * @author Mr.Qu
 * @since 2020/1/10
 */

@Component
public class MQTTListener implements ApplicationListener<ContextRefreshedEvent> {

    private final MQTTConnect server;

    @Autowired
    public MQTTListener(MQTTConnect server) {
        this.server = server;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            server.setMqttClient("admin", "public", new Callback());
            server.sub("com/iot/init");
        } catch (MqttException e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }
}

