package com.example.mqtt.controller;

import com.example.mqtt.Dto.PublishMessageDto;
import com.example.mqtt.entity.MqttSubscribe;
import com.example.mqtt.service.MqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/mqtt")
public class MqttController {
    private final MqttService mqttService;
    @Autowired
    public MqttController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    @PostMapping("/publish")
    public void publishMessage(@RequestBody PublishMessageDto message) throws MqttException, IOException {
        mqttService.publishMessage(message);
    }

    @GetMapping("/subscribe")
    public MqttSubscribe subscribe(@RequestParam(value = "topic") String topic,
                                  @RequestParam(value = "wait_millis") Integer waitMillis) throws MqttException, InterruptedException {
        return mqttService.subscribe(topic,waitMillis);
    }
}
