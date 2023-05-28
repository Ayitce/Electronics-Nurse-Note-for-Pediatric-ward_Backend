package enp.enp_backend.util;

import enp.enp_backend.entity.Patient;
import enp.enp_backend.entity.PatientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    PatientDTO getPatientDTO(Patient patient);
    List<PatientDTO> getPatientDTO(List<Patient> patients);
}
