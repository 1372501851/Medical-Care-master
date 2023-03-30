package com.ruoyi.common.MQTT;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

@Component
public class client3 {
    //主题名
    String topic = "test3";
    //QoS服务质量等级
    int qos = 1;
    //账号
    String userName = "admin";
    //密码
    String password = "public";
    String clientId = "Client3";
    // 内存存储
    MemoryPersistence persistence = new MemoryPersistence();
    //访问服务器地址
    private String broker = "tcp://127.0.0.1:1883";
    // 创建客户端
    MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
    public client3() throws MqttException {
    }

    //初始化设置订阅的回调
    public void init() {
        sampleClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
            }

            //当有订阅的消息时会从这里接收
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("clien3收到主题为:" + topic + "的消息。------\n" + "消息为：:" + new String(message.getPayload()));
            }


            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete---------" + token.isComplete());
            }
        });


    }

    ///
    public void connect() throws MqttException {
        // 创建链接参数
        MqttConnectOptions connOpts = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        connOpts.setCleanSession(true);
        // 设置连接的用户名
        connOpts.setUserName(userName);
        connOpts.setPassword(password.toCharArray());
        // 建立连接
        IMqttToken iMqttToken = sampleClient.connectWithResult(connOpts);
        boolean r = iMqttToken.isComplete();
        if (r) {
            System.out.println("client3连接到服务器成功");
        } else {
            System.out.println("client3连接到服务器失败");
        }
    }

    public void publish(String mes) throws MqttException {
        // 创建消息
        MqttMessage message = new MqttMessage(mes.getBytes());
        // 设置消息的服务质量
        message.setQos(qos);
        // 发布消息
        sampleClient.publish(topic, message);
    }


    public void subscribe(String topic) throws MqttException {
        //订阅消息
        sampleClient.subscribe(topic, qos);
    }

    ///
    public void disconnect() throws MqttException {
        // 断开连接
        sampleClient.disconnect();
        // 关闭客户端
        sampleClient.close();
    }
}

