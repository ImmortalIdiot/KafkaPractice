package com.immortalidiot.service;

import com.immortalidiot.model.Patient;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;

@Slf4j
@Service
public class PatientService {

    private static final String FOLDER_PATH = System.getProperty("user.dir") + File.separator + "generated-patients";

    public boolean generatePatientDocx(Patient patient) {
        File directory = new File(FOLDER_PATH);
        if (!directory.exists() && !directory.mkdirs()) {
            log.error("Failed to create directory: {}", FOLDER_PATH);
            return false;
        }

        String fileName = patient.getId() + ".docx";
        String filePath = FOLDER_PATH + File.separator + fileName;

        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream out = new FileOutputStream(filePath)) {

            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("Patient# " + patient.getId() + " Record");
            titleRun.setBold(false);
            titleRun.setFontSize(14);

            addParagraph(document, "Name: " + patient.getName());
            addParagraph(document, "Age: " + patient.getAge());
            addParagraph(document, "Hospital: " + patient.getHospital());
            addParagraph(document, "Severity: " + patient.getSeverity());
            addParagraph(document, "Diseases: " + patient.getDiseases());

            addParagraph(document, "Symptoms:");
            for (String symptom : patient.getSymptoms()) {
                addParagraph(document, " - " + symptom);
            }

            document.write(out);
            log.info("DOCX file created successfully at: " + filePath);
            return true;

        } catch (Exception e) {
            log.error("Error creating DOCX file", e);
            return false;
        }
    }

    private void addParagraph(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontSize(12);
    }
}
