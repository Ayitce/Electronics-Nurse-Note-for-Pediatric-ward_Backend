package enp.enp_backend.util;

import enp.enp_backend.entity.*;
import enp.enp_backend.security.entity.User;
import enp.enp_backend.security.entity.UserAuthDTO;
import enp.enp_backend.security.entity.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    PatientDTO getPatientDTO(Patient patient);

    List<PatientDTO> getPatientDTO(List<Patient> patients);


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
