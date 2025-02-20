package com.immortalidiot.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.immortalidiot.model.PatientResult;
import com.immortalidiot.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PatientConsumer {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PatientRepository repository;

    @KafkaListener(topics = "${topic-response.name}")
    public void consumeMessage(String message) throws JsonProcessingException {
        var patientResult = mapper.readValue(message, PatientResult.class);
        log.info("ConsumeMessage: {}", message);

        if (!patientResult.getIsCreated()) {
            var patient = repository.findById(patientResult.getId()).orElseThrow();

            patient.setFileCreated(false);
            repository.save(patient);

            log.info("Undo created flag from the patient");
        }
    }
}
