package com.example.mqtt.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "mqttSubscribe")
public class MqttSubscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topik;
    private Integer qos;
    private Integer mqttMessageId;
    private String message;

    public MqttSubscribe() {
    }

    public MqttSubscribe(Long id, String topik, Integer qos, Integer mqttMessageId) {
        this.id = id;
        this.topik = topik;
        this.qos = qos;
        this.mqttMessageId = mqttMessageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopik() {
        return topik;
    }

    public void setTopik(String topik) {
        this.topik = topik;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    public Integer getMqttMessageId() {
        return mqttMessageId;
    }

    public void setMqttMessageId(Integer mqttMessageId) {
        this.mqttMessageId = mqttMessageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
