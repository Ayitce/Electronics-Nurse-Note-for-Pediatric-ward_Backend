package enp.enp_backend.config;

import enp.enp_backend.entity.Patient;
import enp.enp_backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    PatientRepository patientRepository;

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
    }
}
