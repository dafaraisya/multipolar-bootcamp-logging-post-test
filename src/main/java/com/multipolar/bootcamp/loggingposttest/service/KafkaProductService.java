package com.multipolar.bootcamp.loggingposttest.service;

import com.multipolar.bootcamp.loggingposttest.domain.AccessLog;
import com.multipolar.bootcamp.loggingposttest.repository.AccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class KafkaProductService {
    private final ObjectMapper objectMapper;
    private final AccessLogRepository accessLogRepository;

    @Autowired
    public KafkaProductService(ObjectMapper objectMapper, AccessLogRepository accessLogRepository) {
        this.objectMapper = objectMapper;
        this.accessLogRepository = accessLogRepository;
    }

    @KafkaListener(topics = "access-logs-posttest")
    public void receiveMessage(String message) {
        try {
            // Deserialize the JSON message into an AccessLog object
            AccessLog accessLog = objectMapper.readValue(message, AccessLog.class);
            System.out.println("Received message: " + accessLog);
            accessLogRepository.insert(accessLog);
        } catch (Exception e) {
            // Handle any exceptions that may occur during deserialization
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}
