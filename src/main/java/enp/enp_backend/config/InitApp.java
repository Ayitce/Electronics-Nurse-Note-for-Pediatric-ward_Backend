package enp.enp_backend.config;

import enp.enp_backend.domain.doctor.repository.jpa.DoctorRepository;
import enp.enp_backend.domain.nurse.repository.jpa.*;
import enp.enp_backend.entity.*;
import enp.enp_backend.security.entity.Authority;
import enp.enp_backend.security.entity.AuthorityName;
import enp.enp_backend.security.repository.AuthorityRepository;
import enp.enp_backend.domain.user.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;


@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    Nurse_PatientRepository nursePatientRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NurseRepository nurseRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BedRepository bedRepository;

    @Autowired
    Nurse_AdmitRepository nurseAdmitRepository;

    User user1;
    User user2;
    User user3;

    @Autowired
    Nurse_DoctorRepository nurse_doctorRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        Patient patient = Patient.builder()
                .name("Yit")
                .surname("Narak")
                //.age("12")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("brah brah brah")
                .phoneNumber("0936728949")
                //.email("email")
//                .admitDateTime("28-05-2023")
                //.dischargeDate(null)
                .allergies("brah brah")
                // .allergies("-")
                .hn("HN12355")
                .image("")
                .build();

        Patient patient2 = Patient.builder()
                .name("Yit2")
                .surname("Narak")
             //   .age("12")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("brah brah brah")
                .phoneNumber("0936728949")
                // .email("email")
                //    .admitted(true)
                //   .admitDateTime("28-05-2023")
                //   .dischargeDate(null)
                .allergies("brah brah")
                // .allergies("-")
                .hn("HN12354")
                .image("")
                .build();

        Doctor doctor1 = Doctor.builder()
                .name("Suchat")
                .surname("eiei")
                .medicalID("DT15408")
                .build();

        Doctor doctor2 = Doctor.builder()
                .name("Somchai")
                .surname("eaea")
                .medicalID("DT14808")
                .build();

        nurse_doctorRepository.save(doctor1);
        nurse_doctorRepository.save(doctor2);

        patient.setDoctor(doctor1);
        patient2.setDoctor(doctor1);
        nursePatientRepository.save(patient);
        nursePatientRepository.save(patient2);

        Nurse nurse = Nurse.builder()
                .name("Fah")
                .surname("Suaymak")
                .phoneNumber("0801561664")
                .build();
        nurseRepository.save(nurse);

        Doctor doctor = Doctor.builder()
                .name("Yit")
                .surname("Sudsuay")
                .phoneNumber("0801561664")
                .build();


        addUser();

        nurse.setUser(user1);
        nurseRepository.save(nurse);
        user1.setNurse(nurse);
        userRepository.save(user1);

        doctor.setUser(user2);
        doctorRepository.save(doctor);
        user2.setDoctor(doctor);
        userRepository.save(user2);


        Bed bed1 = Bed.builder().isAvailable(false).build();
        Bed bed2 = Bed.builder().isAvailable(false).build();
        Bed bed3 = Bed.builder().isAvailable(true).build();
        Bed bed4 = Bed.builder().isAvailable(true).build();
        Bed bed5 = Bed.builder().isAvailable(true).build();

        bedRepository.save(bed1);
        bedRepository.save(bed2);
        bedRepository.save(bed3);
        bedRepository.save(bed4);
        bedRepository.save(bed5);


        Room room1 = Room.builder().build();
        Room room2 = Room.builder().build();
        room1.getBedList().add(bed1);
        room1.getBedList().add(bed2);
        room1.getBedList().add(bed3);
        room2.getBedList().add(bed4);
        room2.getBedList().add(bed5);
        roomRepository.save(room1);
        roomRepository.save(room2);

        Admit admit1 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(0))
                .patient(patient)
                .an("AN001")
                .age("12 ปี")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit2 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(1))
                .patient(patient2)
                .an("AN002")
                .age("12 ปี")
                .admitDateTime("22-01-2535 12:03:44").build();

        nurseAdmitRepository.save(admit1);
        nurseAdmitRepository.save(admit2);

    }

    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_NURSE).build();
        user1 = User.builder()
                .username("nurse")
                .password(encoder.encode("nurse"))
                //.email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 22).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        authorityRepository.save(authUser);
        user1.getAuthorities().add(authUser);
        userRepository.save(user1);

        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        user2 = User.builder()
                .username("doctor")
                .password(encoder.encode("doctor"))
                //.email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 22).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        authorityRepository.save(authDoctor);
        user2.getAuthorities().add(authDoctor);
        userRepository.save(user2);

        Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        user3 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                //.email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 22).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        authorityRepository.save(authAdmin);
        user3.getAuthorities().add(authAdmin);
        userRepository.save(user3);
    }
}
