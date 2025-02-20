package com.immortalidiot.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.immortalidiot.model.Patient;
import com.immortalidiot.model.PatientResult;
import com.immortalidiot.producer.PatientProducer;
import com.immortalidiot.service.PatientService;
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
    private PatientService patientService;

    @Autowired
    private PatientProducer producer;

    @KafkaListener(topics = "${topic-request.name}")
    public void consumeMessage(String message) throws JsonProcessingException {
        Patient patient = mapper.readValue(message, Patient.class);
        log.info("ConsumeMessage: {}", patient);

        var isCreated = patientService.generatePatientDocx(patient);
        producer.send(new PatientResult(patient.getId(), isCreated));
    }
}
