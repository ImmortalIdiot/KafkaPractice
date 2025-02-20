package com.immortalidiot.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.immortalidiot.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PatientProducer {

    @Value("${topic-request.name}")
    private String orderTopic;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private KafkaTemplate<String, String> template;

    public String send(Patient patient) {
        try {
            String message = mapper.writeValueAsString(patient);
            template.send(orderTopic, message);

            log.info("Patient produced: {}", message);

            return "Message sent";
        } catch (JsonProcessingException e) {
            return "Error parse json";
        }
    }
}
