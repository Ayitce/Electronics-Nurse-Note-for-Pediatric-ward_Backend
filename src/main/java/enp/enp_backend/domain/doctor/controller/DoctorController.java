package enp.enp_backend.domain.doctor.controller;

import enp.enp_backend.domain.doctor.service.DoctorService;
import enp.enp_backend.entity.Admit;
import enp.enp_backend.entity.Patient;
import enp.enp_backend.util.LabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("doctor/admits")
    public ResponseEntity<?> getAdmitLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getAdmitDTO(doctorService.getAllAdmit()));
    }


    @GetMapping("doctor/admits/AN/{an}")
    public ResponseEntity<?> getAdmitByAN(@PathVariable("an") String an) {
        Admit output = doctorService.getAdmitByAn(an);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getAdmitDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }


    @GetMapping("doctor/admits/search")
    public ResponseEntity<?> getSearchedAdmit(@RequestParam(value = "_search", required = false) String search) {
        List<Admit> output = doctorService.getSearchedAdmit(search, search, search, search);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getAdmitDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }

    @GetMapping("doctor/admits/AN/{an}/triages")
    public ResponseEntity<?> getTriageLists(@PathVariable("an") String an) {
        Admit output = doctorService.getAdmitByAn(an);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getTriageDTO(output.getTriages()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }

    @GetMapping("doctor/patient/AN/{an}")
    public ResponseEntity<?> getPatientByAN(@PathVariable("an") String an) {
        Patient output = doctorService.getAdmitByAn(an).getPatient();
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }

}
