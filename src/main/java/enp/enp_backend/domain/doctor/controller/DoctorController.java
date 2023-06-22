package enp.enp_backend.domain.doctor.controller;

import enp.enp_backend.domain.doctor.service.DoctorService;
import enp.enp_backend.entity.Patient;
import enp.enp_backend.util.LabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("doctor/patients")
    public ResponseEntity<?> getPatientLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(doctorService.getAllpatient()));
    }

    @GetMapping("doctor/patients/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") Long id) {
        Patient output = doctorService.getPatient(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}
