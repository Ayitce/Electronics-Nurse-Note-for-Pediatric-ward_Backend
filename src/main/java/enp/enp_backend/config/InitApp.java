package enp.enp_backend.config;

import enp.enp_backend.entity.Patient;
import enp.enp_backend.repository.PatientRepository;
import enp.enp_backend.security.entity.Authority;
import enp.enp_backend.security.entity.AuthorityName;
import enp.enp_backend.security.entity.User;
import enp.enp_backend.security.repository.AuthorityRepository;
import enp.enp_backend.security.repository.UserRepository;
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
    PatientRepository patientRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        Patient patient = Patient.builder()
                .name("Yit")
                .surname("Narak")
                .age(12)
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("brah brah brah")
                .phoneNumber("0936728949")
                .email("email")
                .admitted(true)
                .admitDate("28-05-2023")
                .dischargeDate(null)
                .medicalHistory("brah brah")
                .allergies("-")
                .AN("AN12354")
                .imageUrls("")
                .build();

        patientRepository.save(patient);

        Patient patient2 = Patient.builder()
                .name("Yit2")
                .surname("Narak")
                .age(12)
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("brah brah brah")
                .phoneNumber("0936728949")
                .email("email")
                .admitted(true)
                .admitDate("28-05-2023")
                .dischargeDate(null)
                .medicalHistory("brah brah")
                .allergies("-")
                .AN("AN12354")
                .imageUrls("")
                .build();

        patientRepository.save(patient2);
        addUser();
    }

    User user1;

    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,22).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        authorityRepository.save(authUser);
        user1.getAuthorities().add(authUser);
        userRepository.save(user1);
    }
}
