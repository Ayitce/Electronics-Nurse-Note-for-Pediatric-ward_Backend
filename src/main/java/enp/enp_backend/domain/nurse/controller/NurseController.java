package enp.enp_backend.domain.nurse.controller;

import enp.enp_backend.MedCalculator.MedCalculator;
import enp.enp_backend.domain.nurse.service.NurseService;
import enp.enp_backend.entity.*;
import enp.enp_backend.util.CloudStorageHelper;
import enp.enp_backend.util.LabMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class NurseController {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    NurseService nurseService;

    //------------Image---------------
    @GetMapping("nurse/patients")
    public ResponseEntity<?> getPatientLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(nurseService.getAllpatient()));
    }
/*
    @GetMapping("nurse/patients/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") Long id) {
        Patient output = nurseService.getPatient(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }*/
/*
    @GetMapping("nurse/patients/HN/{hn}")
    public ResponseEntity<?> getPatientByHN(@PathVariable("hn") String hn) {
        Patient output = nurseService.getPatientByHn(hn);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }*/
/*

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
*/
/*
    @GetMapping("nurse/patients/search")
    public ResponseEntity<?> getSearchedPatient(@RequestParam(value = "_search", required = false) String search) throws JSONException {
        List<Patient> output = nurseService.getSearchedPatient(search, search, search);
        logger.info(output);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }*/

    //-----------Image------------
/*

    @PostMapping("/nurse/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException, ServletException {
        return ResponseEntity.ok(this.cloudStorageHelper.getImageUrl(file, "patientimage-53dc6.appspot.com"));
    }
*/

    //--------------Admit-----------------
    @GetMapping("nurse/admits")
    public ResponseEntity<?> getAdmitLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getAdmitDTO(nurseService.getAllAdmit()));
    }
/*

    @GetMapping("nurse/admits/{id}")
    public ResponseEntity<?> getAdmit(@PathVariable("id") Long id) {
        Admit output = nurseService.getAdmit(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getAdmitDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
*/

    @PostMapping("/nurse/admits/discharge")
    public ResponseEntity<?> dischargeAdmit(@RequestBody Admit admit) {
        Admit tempAdmit = nurseService.getAdmit(admit.getId());

        Long bedID = admit.getBed().getId();
        Bed bed = nurseService.getBed(bedID);
        bed.setAvailable(true);
        nurseService.save(bed);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        tempAdmit.setDischargeDate(formatter.format(date));
        nurseService.save(tempAdmit);
        return ResponseEntity.ok(LabMapper.INSTANCE.getAdmitDTO(tempAdmit));
    }

    @PostMapping("/nurse/admits")
    public ResponseEntity<?> addAdmit(@RequestBody Admit admit) {
        Patient tempPatient = admit.getPatient();
        Doctor tempDoctor = nurseService.getDoctor(admit.getPatient().getDoctor().getId());
        tempPatient.setDoctor(tempDoctor);
        nurseService.save(tempPatient);

        Long bedID = admit.getBed().getId();
        Bed bed = nurseService.getBed(bedID);
        bed.setAvailable(false);
        nurseService.save(bed);


        admit.setPatient(tempPatient);
        Admit output = nurseService.save(admit);

        return ResponseEntity.ok(LabMapper.INSTANCE.getAdmitDTO(output));
    }


    @GetMapping("nurse/admits/AN/{an}")
    public ResponseEntity<?> getAdmitByAN(@PathVariable("an") String an) {
        Admit output = nurseService.getAdmitByAn(an);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getAdmitDTO(output));
        } else {
            logger.info("The given AN is not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }


    @GetMapping("nurse/admits/search")
    public ResponseEntity<?> getSearchedAdmit(@RequestParam(value = "_search", required = false) String search) throws JSONException {
        List<Admit> output = nurseService.getSearchedAdmit(search, search, search, search);
        logger.info(output);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getAdmitDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }

    //-------------Room---------------
    @GetMapping("nurse/rooms")
    public ResponseEntity<?> getRoomLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getRoomDTO(nurseService.getAllRoom()));
    }

    @GetMapping("nurse/rooms/{id}")
    public ResponseEntity<?> getRoom(@PathVariable("id") Long id) {
        Room output = nurseService.getRoom(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getRoomDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    //------------Doctor--------------
    @GetMapping("nurse/doctors")
    public ResponseEntity<?> getDoctorLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getDoctorDTO(nurseService.getAllDoctor()));
    }

    //------------Triage--------------
    @GetMapping("nurse/admits/AN/{an}/triages")
    public ResponseEntity<?> getTriageLists(@PathVariable("an") String an) {
        Admit output = nurseService.getAdmitByAn(an);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getTriageDTO(output.getTriages()));
        } else {
            logger.info("The given AN is not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }

    @PostMapping("nurse/admits/AN/{an}/triages")
    public ResponseEntity<?> addTriage(@RequestBody String json, @PathVariable("an") String an) throws JSONException, ParseException {
        JSONObject jsonObject = new JSONObject(json);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Triage triage = Triage.builder()
                .date(formatter.format(date))
                //indicator
                .respiratory(jsonObject.getJSONObject("indicator").getBoolean("respiratory"))
                .sepsis(jsonObject.getJSONObject("indicator").getBoolean("sepsis"))
                .shock(jsonObject.getJSONObject("indicator").getBoolean("shock"))
                .seizure(jsonObject.getJSONObject("indicator").getBoolean("seizure"))
                //vitalsign
                .heartRate(jsonObject.getJSONObject("vitalSign").getInt("heartRate"))
                .respiratoryRate(jsonObject.getJSONObject("vitalSign").getInt("respiratoryRate"))
                .temperature(jsonObject.getJSONObject("vitalSign").getDouble("temperature"))
                .oxygenSaturation(jsonObject.getJSONObject("vitalSign").getInt("oxygenSaturation"))
                .oxygenTherapy(jsonObject.getJSONObject("vitalSign").getInt("oxygenTherapy"))
                .systolic_blood_pressure(jsonObject.getJSONObject("vitalSign").getInt("systolic_blood_pressure"))
                .diastolic_blood_pressure(jsonObject.getJSONObject("vitalSign").getInt("diastolic_blood_pressure"))
                //add
                .poor_feeding(jsonObject.getJSONObject("add").getBoolean("poor_feeding"))
                .history_of_seizure(jsonObject.getJSONObject("add").getBoolean("history_of_seizure"))
                .generalize_seizure(jsonObject.getJSONObject("add").getBoolean("generalize_seizure"))
                .comoatose_stage_seizure(jsonObject.getJSONObject("add").getBoolean("comoatose_stage_seizure"))
                .GCS(jsonObject.getJSONObject("add").getInt("gcs"))
                //initial impression
                .scalene_muscle(jsonObject.getJSONObject("initialImpression").getBoolean("scalene_muscle"))
                .irritable(jsonObject.getJSONObject("initialImpression").getBoolean("irritable"))
                .stupor_drownsiness(jsonObject.getJSONObject("initialImpression").getBoolean("stupor_drownsiness"))
                .dehedration(jsonObject.getJSONObject("initialImpression").getBoolean("dehedration"))
                .nasal_flaring(jsonObject.getJSONObject("initialImpression").getBoolean("nasal_flaring"))
                .subcostral_retraction(jsonObject.getJSONObject("initialImpression").getBoolean("subcostral_retraction"))
                .supersternal_retraction(jsonObject.getJSONObject("initialImpression").getBoolean("supersternal_retraction"))
                .grunting(jsonObject.getJSONObject("initialImpression").getBoolean("grunting"))
                .pale_cyanosis(jsonObject.getJSONObject("initialImpression").getBoolean("pale_cyanosis"))
                .motting_skin(jsonObject.getJSONObject("initialImpression").getBoolean("motting_skin"))
                .petichea(jsonObject.getJSONObject("initialImpression").getBoolean("petichea"))
                //risk factor
                .suspected_infection(jsonObject.getJSONObject("riskFactor").getBoolean("suspected_infection"))
                .organtranplantation(jsonObject.getJSONObject("riskFactor").getBoolean("organtranplantation"))
                .history_bone_marrow(jsonObject.getJSONObject("riskFactor").getBoolean("history_bone_marrow"))
                .primary_immune_defencing(jsonObject.getJSONObject("riskFactor").getBoolean("primary_immune_defencing"))
                .postSplenectomy_asplenia(jsonObject.getJSONObject("riskFactor").getBoolean("postSplenectomy_asplenia"))
                .malignancy(jsonObject.getJSONObject("riskFactor").getBoolean("malignancy"))
                .bedRidden_cerebralPulsy(jsonObject.getJSONObject("riskFactor").getBoolean("bedRidden_cerebralPulsy"))
                .center_iv_catheter(jsonObject.getJSONObject("riskFactor").getBoolean("center_iv_catheter"))
                //physical exam
                .weak_pulse(jsonObject.getJSONObject("physicalExam").getBoolean("weak_pulse"))
                .bounding_pulse(jsonObject.getJSONObject("physicalExam").getBoolean("bounding_pulse"))
                .cap_refill(jsonObject.getJSONObject("physicalExam").getBoolean("cap_refill"))
                .flash_cap(jsonObject.getJSONObject("physicalExam").getBoolean("flash_cap"))
                .consciousness(Consciousness.valueOf(jsonObject.getJSONObject("physicalExam").getString("consciousness")))
                .airEntry(jsonObject.getJSONObject("physicalExam").getInt("airEntry"))
                .wheezing(jsonObject.getJSONObject("physicalExam").getInt("wheezing"))
                .build();
        Admit admit = nurseService.getAdmitByAn(an);
        triage.setAdmit(admit);

        MedCalculator medCalculator = new MedCalculator(triage);
        triage.setMpew(medCalculator.getMPEWS());
        if (jsonObject.getJSONObject("indicator").getBoolean("respiratory"))
            triage.setResult_respiratory(medCalculator.getPress());
        if (jsonObject.getJSONObject("indicator").getBoolean("sepsis"))
            triage.setResult_sepsis(medCalculator.getSepsis());
        if (jsonObject.getJSONObject("indicator").getBoolean("shock"))
            triage.setResult_shock(medCalculator.getShock());
        if (jsonObject.getJSONObject("indicator").getBoolean("seizure"))
            triage.setResult_seizure(medCalculator.getSeisure());


        nurseService.save(triage);
        admit.getTriages().add(triage);
        nurseService.save(admit);
        //nurseService.save(triage);

        return ResponseEntity.ok(LabMapper.INSTANCE.getTriageDTO(triage));
    }

}
