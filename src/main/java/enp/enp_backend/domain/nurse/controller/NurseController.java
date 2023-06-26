package enp.enp_backend.domain.nurse.controller;

import enp.enp_backend.domain.nurse.service.NurseService;
import enp.enp_backend.entity.Patient;
import enp.enp_backend.util.CloudStorageHelper;
import enp.enp_backend.util.LabMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class NurseController {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    NurseService nurseService;

    @GetMapping("nurse/patients")
    public ResponseEntity<?> getPatientLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(nurseService.getAllpatient()));
    }

    @GetMapping("nurse/patients/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") Long id) {
        Patient output = nurseService.getPatient(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @GetMapping("nurse/patients/AN/{an}")
    public ResponseEntity<?> getPatientByAN(@PathVariable("an") String an) {
        Patient output = nurseService.getPatientByAn(an);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }

    @GetMapping("nurse/patients/search")
    public ResponseEntity<?> getSearchedPatient(@RequestParam(value = "_search", required = false) String search) throws JSONException {
        List<Patient> output = nurseService.getSearchedPatient(search,search,search);
        logger.info(output);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }

    @PostMapping("/nurse/patients")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        patient.setAdmitted(true);
        Patient output = nurseService.save(patient);
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
    }

    @PostMapping("/nurse/patients/discharge")
    public ResponseEntity<?> dischargePatient(@RequestBody Patient patient) {
        Patient tempPatient = nurseService.getPatient(patient.getId());
        tempPatient.setAdmitted(false);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        tempPatient.setDischargeDate(formatter.format(date));
        nurseService.save(tempPatient);
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(tempPatient));
    }

    //---------------------------
    @Autowired
    CloudStorageHelper cloudStorageHelper;

    @PostMapping("/nurse/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException, ServletException {
        return ResponseEntity.ok(this.cloudStorageHelper.getImageUrl(file, "patientimage-53dc6.appspot.com"));
    }
}
