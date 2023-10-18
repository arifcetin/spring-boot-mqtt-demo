package com.example.mqtt.config;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttClientConfig {
    private static MqttClient mqttClient;

    public MqttClientConfig() {
    }

    public static IMqttClient getInstance(){
        try {
            if (mqttClient == null){
                mqttClient = new MqttClient("tcp://broker.mqttdashboard.com:1883",
                        MqttClient.generateClientId(), new MemoryPersistence());
            }
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(false);
            options.setConnectionTimeout(10);
            if (!mqttClient.isConnected()) {
                mqttClient.connect(options);
            }
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        return mqttClient;
    }
}
