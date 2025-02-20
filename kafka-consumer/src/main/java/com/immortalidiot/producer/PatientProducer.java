package com.immortalidiot.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.immortalidiot.model.PatientResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PatientProducer {

    @Value("${topic-response.name}")
    private String orderTopic;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String send(PatientResult result) throws JsonProcessingException {
        var message = objectMapper.writeValueAsString(result);
        kafkaTemplate.send(orderTopic, message);

        log.info("send: {}", message);

        return "Message sent";
    }
}
