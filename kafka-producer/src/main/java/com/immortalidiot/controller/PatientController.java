package com.immortalidiot.controller;

import com.immortalidiot.model.PatientRequest;
import com.immortalidiot.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping
    public String getRequest() {
        return "All works";
    }

    @PostMapping
    public String postRequest(@RequestBody PatientRequest patient) {
        return service.processPatient(patient);
    }
}
