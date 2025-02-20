package com.immortalidiot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "patients")
public class Patient {

    @Id
    private String id;
    private String name;
    private String diseases;
    private String hospital;
    private List<String> symptoms;
    private int age;
    private String severity;
    private Boolean fileCreated;

    protected Patient() {
    }

    public Patient(String id,
                   String name,
                   String diseases,
                   String hospital,
                   List<String> symptoms,
                   int age,
                   String severity) {
        this.id = id;
        this.name = name;
        this.diseases = diseases;
        this.hospital = hospital;
        this.symptoms = symptoms;
        this.age = age;
        this.severity = severity;
        this.fileCreated = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Boolean getFileCreated() {
        return fileCreated;
    }

    public void setFileCreated(Boolean fileCreated) {
        this.fileCreated = fileCreated;
    }
}
