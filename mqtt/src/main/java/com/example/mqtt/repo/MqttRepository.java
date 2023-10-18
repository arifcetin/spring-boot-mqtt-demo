package com.example.mqtt.repo;

import com.example.mqtt.entity.MqttSubscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MqttRepository extends JpaRepository<MqttSubscribe,Long> {
}
