package enp.enp_backend.util;

import enp.enp_backend.entity.*;
import enp.enp_backend.entity.User;
import enp.enp_backend.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    PatientDTO getPatientDTO(Patient patient);

    List<PatientDTO> getPatientDTO(List<Patient> patients);

    AdmitDTO getAdmitDTO(Admit admit);

    List<AdmitDTO> getAdmitDTO(List<Admit> admits);

    RoomDTO getRoomDTO(Room room);

    List<RoomDTO> getRoomDTO(List<Room> rooms);

    DoctorDTO getDoctorDTO(Doctor doctor);

    List<DoctorDTO> getDoctorDTO(List<Doctor> doctor);

    IndicatorDTO getIndicatorDTO(Triage triage);

    InitialImpressionDTO getInitialImpressionDTO(Triage triage);

    RiskFactorDTO getRiskFactorDTO(Triage triage);

    ADDDTO getAddDTO(Triage triage);

    VitalSignDTO getVitalSignDTO(Triage triage);

    PhysicalExamDTO getPhysicalExamDTO(Triage triage);

    @Mapping(source = "respiratory", target = "indicator.respiratory")
    @Mapping(source = "sepsis", target = "indicator.sepsis")
    @Mapping(source = "shock", target = "indicator.shock")
    @Mapping(source = "seizure", target = "indicator.seizure")
    @Mapping(source = "heartRate", target = "vitalSign.heartRate")
    @Mapping(source = "respiratoryRate", target = "vitalSign.respiratoryRate")
    @Mapping(source = "temperature", target = "vitalSign.temperature")
    @Mapping(source = "oxygenSaturation", target = "vitalSign.oxygenSaturation")
    @Mapping(source = "oxygenTherapy", target = "vitalSign.oxygenTherapy")
    @Mapping(source = "systolic_blood_pressure", target = "vitalSign.systolic_blood_pressure")
    @Mapping(source = "diastolic_blood_pressure", target = "vitalSign.diastolic_blood_pressure")
    @Mapping(source = "poor_feeding", target = "add.poor_feeding")
    @Mapping(source = "history_of_seizure", target = "add.history_of_seizure")
    @Mapping(source = "generalize_seizure", target = "add.generalize_seizure")
    @Mapping(source = "comoatose_stage_seizure", target = "add.comoatose_stage_seizure")
    @Mapping(source = "GCS", target = "add.GCS")
    @Mapping(source = "e", target = "add.e")
    @Mapping(source = "v", target = "add.v")
    @Mapping(source = "m", target = "add.m")
    @Mapping(source = "scalene_muscle", target = "initialImpression.scalene_muscle")
    @Mapping(source = "irritable", target = "initialImpression.irritable")
    @Mapping(source = "stupor_drownsiness", target = "initialImpression.stupor_drownsiness")
    @Mapping(source = "dehedration", target = "initialImpression.dehedration")
    @Mapping(source = "nasal_flaring", target = "initialImpression.nasal_flaring")
    @Mapping(source = "subcostral_retraction", target = "initialImpression.subcostral_retraction")
    @Mapping(source = "supersternal_retraction", target = "initialImpression.supersternal_retraction")
    @Mapping(source = "grunting", target = "initialImpression.grunting")
    @Mapping(source = "pale_cyanosis", target = "initialImpression.pale_cyanosis")
    @Mapping(source = "motting_skin", target = "initialImpression.motting_skin")
    @Mapping(source = "petichea", target = "initialImpression.petichea")
    @Mapping(source = "weak_pulse", target = "physicalExam.weak_pulse")
    @Mapping(source = "bounding_pulse", target = "physicalExam.bounding_pulse")
    @Mapping(source = "cap_refill", target = "physicalExam.cap_refill")
    @Mapping(source = "flash_cap", target = "physicalExam.flash_cap")
    @Mapping(source = "consciousness", target = "physicalExam.consciousness")
    @Mapping(source = "airEntry", target = "physicalExam.airEntry")
    @Mapping(source = "wheezing", target = "physicalExam.wheezing")
    @Mapping(source = "suspected_infection", target = "riskFactor.suspected_infection")
    @Mapping(source = "organtranplantation", target = "riskFactor.organtranplantation")
    @Mapping(source = "history_bone_marrow", target = "riskFactor.history_bone_marrow")
    @Mapping(source = "primary_immune_defencing", target = "riskFactor.primary_immune_defencing")
    @Mapping(source = "postSplenectomy_asplenia", target = "riskFactor.postSplenectomy_asplenia")
    @Mapping(source = "malignancy", target = "riskFactor.malignancy")
    @Mapping(source = "bedRidden_cerebralPulsy", target = "riskFactor.bedRidden_cerebralPulsy")
    @Mapping(source = "center_iv_catheter", target = "riskFactor.center_iv_catheter")
    @Mapping(source = "mpew", target = "triageResult.mpew")
    @Mapping(source = "severity", target = "triageResult.severity")
    @Mapping(source = "result_respiratory", target = "triageResult.result_respiratory")
    @Mapping(source = "result_sepsis", target = "triageResult.result_sepsis")
    @Mapping(source = "result_shock", target = "triageResult.result_shock")
    @Mapping(source = "result_seizure", target = "triageResult.result_seizure")
    TriageDTO getTriageDTO(Triage triage);

    List<TriageDTO> getTriageDTO(List<Triage> triage);

    @Mapping(target = "authorities", expression = "java(user.getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    UserDTO getUserDTO(User user);

    List<UserDTO> getUserDTO(List<User> users);

    @Mapping(target = "authorities", expression = "java(user.getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    UserAuthDTO getUserAuthDTO(User user);

    @Mapping(target = "authorities", expression = "java(nurse.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    NurseAuthDTO getNurseAuthDTO(Nurse nurse);


    @Mapping(target = "authorities", expression = "java(doctor.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    DoctorAuthDTO getDoctorAuthDTO(Doctor doctor);

}
