package com.example.mqtt.service;

import com.example.mqtt.Dto.PublishMessageDto;
import com.example.mqtt.config.MqttClientConfig;
import com.example.mqtt.entity.MqttSubscribe;
import com.example.mqtt.repo.MqttRepository;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class MqttService {
    private final MqttRepository mqttRepository;
    @Value("${mqtt.topic}")
    private String topic;
    @Value("${mqtt.qos}")
    private Integer qos;
    @Value("${mqtt.retained}")
    private boolean retained;
    @Autowired
    public MqttService(MqttRepository mqttRepository) {
        this.mqttRepository = mqttRepository;
    }

    public void publishMessage(PublishMessageDto message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getMessage().getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        MqttClientConfig.getInstance().publish(topic, mqttMessage);
    }

    public MqttSubscribe subscribe(String topic, Integer waitMillis) throws MqttException, InterruptedException {
        MqttSubscribe mqttSubscribe = new MqttSubscribe();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        MqttClientConfig.getInstance().subscribeWithResponse(topic,2, (s, mqttMessage) -> {
            mqttSubscribe.setMqttMessageId(mqttMessage.getId());
            mqttSubscribe.setMessage(new String(mqttMessage.getPayload()));
            mqttSubscribe.setQos(mqttMessage.getQos());
            mqttSubscribe.setTopik(topic);
            mqttRepository.save(mqttSubscribe);
            countDownLatch.countDown();

        });
        countDownLatch.await(waitMillis, TimeUnit.MILLISECONDS);
        return mqttSubscribe;
    }
}



















