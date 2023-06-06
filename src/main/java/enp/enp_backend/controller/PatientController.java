package enp.enp_backend.controller;

import enp.enp_backend.entity.Patient;
import enp.enp_backend.service.PatientService;
import enp.enp_backend.util.LabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("patients")
    public ResponseEntity<?> getPatientLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(patientService.getAllpatient()));
    }

    @GetMapping("patients/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") Long id){
        Patient output = patientService.getPatient(id);
        if(output != null){
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }

    @PostMapping("/patients")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        patient.setAdmitDate(formatter.format(date));
        patient.setAdmitted(true);
        Patient output = patientService.save(patient);
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
    }

    @PostMapping("/patients/discharge")
    public ResponseEntity<?> dischargePatient(@RequestBody Patient patient) {
        Patient tempPatient = patientService.getPatient(patient.getId());
        tempPatient.setAdmitted(false);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        tempPatient.setDischargeDate(formatter.format(date));
        patientService.save(tempPatient);
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(tempPatient));
    }
}
