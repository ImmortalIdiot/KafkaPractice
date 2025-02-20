package com.immortalidiot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {
    private String id;
    private String name;
    private String diseases;
    private String hospital;
    private List<String> symptoms;
    private int age;
    private String severity;
}
