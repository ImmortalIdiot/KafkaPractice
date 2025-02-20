package com.immortalidiot.service;

import com.immortalidiot.model.Patient;
import com.immortalidiot.model.PatientRequest;
import com.immortalidiot.producer.PatientProducer;
import com.immortalidiot.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PatientService {

    @Autowired
    private PatientProducer producer;

    @Autowired
    private PatientRepository repository;

    public String processPatient(PatientRequest patientRequest) {
        Patient patient = repository.findById(patientRequest.getId()).orElseThrow();
        log.info("Patients are received by kafka-producer: {}", patient);

        if (patient.getFileCreated() == null) { patient.setFileCreated(false); }
        if (patient.getFileCreated()) { return "File already exists"; }

        patient.setFileCreated(true);
        repository.save(patient);

        return producer.send(patient);
    }
}
