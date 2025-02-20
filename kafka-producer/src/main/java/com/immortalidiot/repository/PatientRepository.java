package com.immortalidiot.repository;

import com.immortalidiot.model.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {}
