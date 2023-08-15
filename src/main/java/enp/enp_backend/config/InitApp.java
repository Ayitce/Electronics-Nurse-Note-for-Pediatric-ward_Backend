package enp.enp_backend.config;

import enp.enp_backend.MedCalculator.IMedCalculator;
import enp.enp_backend.MedUtils.Sepsis;
import enp.enp_backend.MedUtils.Shock;
import enp.enp_backend.domain.doctor.repository.jpa.DoctorRepository;
import enp.enp_backend.domain.nurse.repository.jpa.*;
import enp.enp_backend.entity.*;
import enp.enp_backend.MedUtils.MPEWBean;
import enp.enp_backend.security.entity.Authority;
import enp.enp_backend.security.entity.AuthorityName;
import enp.enp_backend.security.repository.AuthorityRepository;
import enp.enp_backend.domain.user.repository.jpa.UserRepository;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;


@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    private final Log logger = LogFactory.getLog(this.getClass());
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

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

    IMedCalculator iMedCalculator;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        Patient patient = Patient.builder()
                .name("Somchai")
                .surname("Duangjai")
                .gender("Male")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN12355")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient2 = Patient.builder()
                .name("SomYing")
                .surname("Duangjai")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN12354")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();


        Patient patient3 = Patient.builder()
                .name("Suchat")
                .surname("Duangjai")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN12344")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();


        Patient patient4 = Patient.builder()
                .name("Suchat")
                .surname("Dsadwqi")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN12378")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient5 = Patient.builder()
                .name("Suha")
                .surname("Dngjai")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN12844")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();


        Patient patient6 = Patient.builder()
                .name("Uchat")
                .surname("Duangjai")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN12474")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();


        Patient patient7 = Patient.builder()
                .name("Johnathan")
                .surname("Martinez")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN12478")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient8 = Patient.builder()
                .name("Kane")
                .surname("Farley")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN12048")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient9 = Patient.builder()
                .name("Maariyah")
                .surname("Patton")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN45378")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient10 = Patient.builder()
                .name("Zaynah")
                .surname("Ramirez")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN45478")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient11 = Patient.builder()
                .name("Abraham")
                .surname("Abraham")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN45878")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient12 = Patient.builder()
                .name("Sam")
                .surname("Morgan")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN45178")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient13 = Patient.builder()
                .name("Faris")
                .surname("Barron")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN45828")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient14 = Patient.builder()
                .name("Antonio")
                .surname("Adams")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN45873")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient15 = Patient.builder()
                .name("Frazer")
                .surname("Barber")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN42878")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient16 = Patient.builder()
                .name("Keyaan")
                .surname("Howell")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN55878")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient17 = Patient.builder()
                .name("Norma")
                .surname("Shelton")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN48878")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient18 = Patient.builder()
                .name("Cyrus")
                .surname("Shelton")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN48678")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient19 = Patient.builder()
                .name("Amanda")
                .surname("SNicholson")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN18978")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();


        Patient patient20 = Patient.builder()
                .name("Callan")
                .surname("Nichols")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN13578")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();


        Patient patient21 = Patient.builder()
                .name("Sapphire")
                .surname("Mccormick")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN75878")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient22 = Patient.builder()
                .name("Gerald")
                .surname("Chandler")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN45170")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient23 = Patient.builder()
                .name("Betsy")
                .surname("Valentine")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN45148")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient24 = Patient.builder()
                .name("Barry")
                .surname("Mosley")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN11873")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient25 = Patient.builder()
                .name("Cody")
                .surname("Bloggs")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN00001")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient26 = Patient.builder()
                .name("Fern")
                .surname("Fulton")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN5178")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient27 = Patient.builder()
                .name("NArabella")
                .surname("Shelton")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN48028")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient28 = Patient.builder()
                .name("Boyle")
                .surname("Shelton")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN48952")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Patient patient29 = Patient.builder()
                .name("Amanda")
                .surname("SNicholson")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN11478")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();


        Patient patient30 = Patient.builder()
                .name("Callan")
                .surname("Nichols")
                .gender("Female")
                .dateOfBirth("24-08-2001")
                .address("24 Soi Lasalle 18, Bangna, Bangna")
                .phoneNumber("0936728949")
                .allergies("none")
                .bloodType("B")
                .height("125")
                .weight("30")
                .idCard("132548743")
                .parentName("Mary Duangjai")
                .symptom("fever")
                .hn("HN13457")
                .image("sharp-fade-with-straight-fringe-for-boys.jpg")
                .build();

        Doctor doctor1 = Doctor.builder()
                .name("John")
                .surname("Son")
                .medicalID("243599")
                .phoneNumber("0801561664")
                .gender("Male")
                .build();

        Doctor doctor2 = Doctor.builder()
                .name("James")
                .surname("Wood")
                .medicalID("789456")
                .phoneNumber("0964537845")
                .gender("Male")
                .build();

        nurse_doctorRepository.save(doctor1);
        nurse_doctorRepository.save(doctor2);

        patient.setDoctor(doctor1);
        patient2.setDoctor(doctor1);
        patient3.setDoctor(doctor2);
        patient4.setDoctor(doctor2);
        patient5.setDoctor(doctor2);
        patient6.setDoctor(doctor2);
        patient7.setDoctor(doctor1);
        patient8.setDoctor(doctor1);
        patient9.setDoctor(doctor2);
        patient10.setDoctor(doctor2);
        patient11.setDoctor(doctor2);
        patient12.setDoctor(doctor2);
        patient13.setDoctor(doctor2);
        patient14.setDoctor(doctor1);
        patient15.setDoctor(doctor1);
        patient16.setDoctor(doctor2);
        patient17.setDoctor(doctor2);
        patient18.setDoctor(doctor2);
        patient19.setDoctor(doctor2);
        patient20.setDoctor(doctor2);

        patient21.setDoctor(doctor2);
        patient22.setDoctor(doctor2);
        patient23.setDoctor(doctor2);
        patient24.setDoctor(doctor1);
        patient25.setDoctor(doctor1);
        patient26.setDoctor(doctor2);
        patient27.setDoctor(doctor2);
        patient28.setDoctor(doctor2);
        patient29.setDoctor(doctor2);
        patient30.setDoctor(doctor2);
        nursePatientRepository.save(patient);
        nursePatientRepository.save(patient2);
        nursePatientRepository.save(patient3);
        nursePatientRepository.save(patient4);
        nursePatientRepository.save(patient5);
        nursePatientRepository.save(patient6);

        nursePatientRepository.save(patient7);
        nursePatientRepository.save(patient8);
        nursePatientRepository.save(patient9);
        nursePatientRepository.save(patient10);
        nursePatientRepository.save(patient11);
        nursePatientRepository.save(patient12);
        nursePatientRepository.save(patient13);
        nursePatientRepository.save(patient14);
        nursePatientRepository.save(patient15);
        nursePatientRepository.save(patient16);
        nursePatientRepository.save(patient17);
        nursePatientRepository.save(patient18);
        nursePatientRepository.save(patient19);
        nursePatientRepository.save(patient20);

        nursePatientRepository.save(patient21);
        nursePatientRepository.save(patient22);
        nursePatientRepository.save(patient23);
        nursePatientRepository.save(patient24);
        nursePatientRepository.save(patient25);
        nursePatientRepository.save(patient26);
        nursePatientRepository.save(patient27);
        nursePatientRepository.save(patient28);
        nursePatientRepository.save(patient29);
        nursePatientRepository.save(patient30);

        Nurse nurse = Nurse.builder()
                .name("John")
                .surname("Son")
                .phoneNumber("0801561664")
                .medicalID("215499")
                .gender("Male")
                .build();
        nurseRepository.save(nurse);

        Doctor doctor = Doctor.builder()
                .name("William")
                .surname("Billiam")
                .phoneNumber("0568745378")
                .medicalID("132478")
                .gender("Male")
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
        //------------------------------------------------------
        /*Bed bed3 = Bed.builder().isAvailable(true).build();
        Bed bed4 = Bed.builder().isAvailable(true).build();
        Bed bed5 = Bed.builder().isAvailable(true).build();*/
        Bed bed3 = Bed.builder().isAvailable(false).build();
        Bed bed4 = Bed.builder().isAvailable(false).build();
        Bed bed5 = Bed.builder().isAvailable(false).build();
        Bed bed6 = Bed.builder().isAvailable(false).build();
        Bed bed7 = Bed.builder().isAvailable(false).build();
        Bed bed8 = Bed.builder().isAvailable(false).build();

        Bed bed9 = Bed.builder().isAvailable(false).build();
        Bed bed10 = Bed.builder().isAvailable(false).build();
        Bed bed11 = Bed.builder().isAvailable(false).build();
        Bed bed12 = Bed.builder().isAvailable(false).build();
        Bed bed13 = Bed.builder().isAvailable(false).build();
        Bed bed14 = Bed.builder().isAvailable(false).build();
        Bed bed15 = Bed.builder().isAvailable(false).build();
        Bed bed16 = Bed.builder().isAvailable(false).build();
        Bed bed17 = Bed.builder().isAvailable(false).build();
        Bed bed18 = Bed.builder().isAvailable(false).build();
        Bed bed19 = Bed.builder().isAvailable(false).build();
        Bed bed20 = Bed.builder().isAvailable(false).build();
        Bed bed21 = Bed.builder().isAvailable(false).build();
        Bed bed22 = Bed.builder().isAvailable(false).build();
        Bed bed23 = Bed.builder().isAvailable(false).build();
        Bed bed24 = Bed.builder().isAvailable(false).build();
        Bed bed25 = Bed.builder().isAvailable(false).build();
        Bed bed26 = Bed.builder().isAvailable(false).build();
        Bed bed27 = Bed.builder().isAvailable(false).build();
        Bed bed28 = Bed.builder().isAvailable(false).build();
        Bed bed29 = Bed.builder().isAvailable(false).build();
        Bed bed30 = Bed.builder().isAvailable(false).build();

        Bed bed31 = Bed.builder().isAvailable(true).build();
        Bed bed32 = Bed.builder().isAvailable(true).build();
        Bed bed33 = Bed.builder().isAvailable(true).build();
        Bed bed34 = Bed.builder().isAvailable(true).build();
        Bed bed35 = Bed.builder().isAvailable(true).build();

        bedRepository.save(bed1);
        bedRepository.save(bed2);
        bedRepository.save(bed3);
        bedRepository.save(bed4);
        bedRepository.save(bed5);
        bedRepository.save(bed6);
        bedRepository.save(bed7);
        bedRepository.save(bed8);

        bedRepository.save(bed9);
        bedRepository.save(bed10);
        bedRepository.save(bed11);
        bedRepository.save(bed12);
        bedRepository.save(bed13);
        bedRepository.save(bed14);
        bedRepository.save(bed15);
        bedRepository.save(bed16);
        bedRepository.save(bed17);
        bedRepository.save(bed18);
        bedRepository.save(bed19);
        bedRepository.save(bed20);

        bedRepository.save(bed21);
        bedRepository.save(bed22);
        bedRepository.save(bed23);
        bedRepository.save(bed24);
        bedRepository.save(bed25);
        bedRepository.save(bed26);
        bedRepository.save(bed27);
        bedRepository.save(bed28);
        bedRepository.save(bed29);
        bedRepository.save(bed30);
        bedRepository.save(bed31);
        bedRepository.save(bed32);
        bedRepository.save(bed33);
        bedRepository.save(bed34);
        bedRepository.save(bed35);

        Room room1 = Room.builder().build();
        Room room2 = Room.builder().build();
        room1.getBedList().add(bed1);
        room1.getBedList().add(bed2);
        room1.getBedList().add(bed3);
        room1.getBedList().add(bed6);
        room1.getBedList().add(bed7);
        room1.getBedList().add(bed4);
        room1.getBedList().add(bed5);
        room1.getBedList().add(bed8);

        room1.getBedList().add(bed9);
        room1.getBedList().add(bed10);
        room1.getBedList().add(bed11);
        room1.getBedList().add(bed12);
        room1.getBedList().add(bed13);
        room1.getBedList().add(bed14);
        room1.getBedList().add(bed15);
        room1.getBedList().add(bed16);
        room1.getBedList().add(bed17);
        room1.getBedList().add(bed18);
        room1.getBedList().add(bed19);
        room1.getBedList().add(bed20);

        room1.getBedList().add(bed21);
        room1.getBedList().add(bed22);
        room1.getBedList().add(bed23);
        room1.getBedList().add(bed24);
        room1.getBedList().add(bed25);
        room1.getBedList().add(bed26);
        room1.getBedList().add(bed27);
        room1.getBedList().add(bed28);
        room1.getBedList().add(bed29);
        room1.getBedList().add(bed30);
        room1.getBedList().add(bed31);
        room1.getBedList().add(bed32);

        room1.getBedList().add(bed33);
        room1.getBedList().add(bed34);
        room1.getBedList().add(bed35);
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

        Admit admit3 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(2))
                .patient(patient3)
                .an("AN006")
                .age("12 ปี")
                .admitDateTime("22-01-2535 12:03:44")
                .dischargeDate("23-01-2535 12:03:44").build();

        Admit admit4 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(3))
                .patient(patient4)
                .an("AN046")
                .age("12 ปี")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit5 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(4))
                .patient(patient5)
                .an("AN806")
                .age("12 ปี")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit6 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(5))
                .patient(patient6)
                .an("AN096")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit7 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(6))
                .patient(patient7)
                .an("AN016")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit8 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(7))
                .patient(patient8)
                .an("AN816")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();


        Admit admit9 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(8))
                .patient(patient9)
                .an("AN1116")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit10 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(9))
                .patient(patient10)
                .an("AN1216")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit11 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(10))
                .patient(patient11)
                .an("AN12167")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit12 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(11))
                .patient(patient12)
                .an("AN12117")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit13 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(12))
                .patient(patient13)
                .an("AN1717")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit14 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(13))
                .patient(patient14)
                .an("AN1727")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit15 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(14))
                .patient(patient15)
                .an("AN1724")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();


        Admit admit16 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(15))
                .patient(patient16)
                .an("AN17244")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit17 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(16))
                .patient(patient17)
                .an("AN144")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit18 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(17))
                .patient(patient18)
                .an("AN1442")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit19 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(18))
                .patient(patient19)
                .an("AN142")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit20 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(19))
                .patient(patient20)
                .an("AN14472")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit21 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(20))
                .patient(patient21)
                .an("AN167")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit22 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(21))
                .patient(patient22)
                .an("AN17717")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit23 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(22))
                .patient(patient23)
                .an("AN12217")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit24 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(23))
                .patient(patient24)
                .an("AN14427")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit25 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(24))
                .patient(patient25)
                .an("AN124")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();


        Admit admit26 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(25))
                .patient(patient26)
                .an("AN11144")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit27 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(26))
                .patient(patient27)
                .an("AN177744")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit28 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(27))
                .patient(patient28)
                .an("AN144277")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit29 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(28))
                .patient(patient29)
                .an("AN142787")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit30 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(29))
                .patient(patient30)
                .an("AN14572")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();


        Admit admit31 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(4))
                .patient(patient5)
                .an("AN8116")
                .age("12 ปี")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit32 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(5))
                .patient(patient6)
                .an("AN1246")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit33 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(6))
                .patient(patient7)
                .an("AN676")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit34 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(7))
                .patient(patient8)
                .an("AN8446")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();


        Admit admit35 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(8))
                .patient(patient9)
                .an("AN1416")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit36 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(9))
                .patient(patient10)
                .an("AN12126")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit37 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(10))
                .patient(patient11)
                .an("AN67")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit38 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(11))
                .patient(patient12)
                .an("AN17517")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit39 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(12))
                .patient(patient13)
                .an("AN17899")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit40 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(13))
                .patient(patient14)
                .an("AN17277")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit41 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(14))
                .patient(patient15)
                .an("AN1424")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();


        Admit admit42 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(15))
                .patient(patient16)
                .an("AN17294")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit43 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(16))
                .patient(patient17)
                .an("AN144774")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit44 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(17))
                .patient(patient18)
                .an("AN1352")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit45 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(18))
                .patient(patient19)
                .an("AN148792")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit46 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(19))
                .patient(patient20)
                .an("AN1412")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit47 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(20))
                .patient(patient21)
                .an("AN797")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit48 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(21))
                .patient(patient22)
                .an("AN78717")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit49 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(22))
                .patient(patient23)
                .an("AN00530")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit50 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(23))
                .patient(patient24)
                .an("AN42027")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit51 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(24))
                .patient(patient25)
                .an("AN044")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();


        Admit admit52 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(25))
                .patient(patient26)
                .an("AN117844")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit53 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(26))
                .patient(patient27)
                .an("AN1744")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit54 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(27))
                .patient(patient28)
                .an("AN17")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit55 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(28))
                .patient(patient29)
                .an("AN00424")
                .age("0 ปี 3 เดือน")
                .dischargeDate("23-01-2535 12:03:44")
                .admitDateTime("22-01-2535 12:03:44").build();

        Admit admit56 = Admit.builder()
                .room(room1)
                .bed(room1.getBedList().get(29))
                .patient(patient30)
                .an("AN1400")
                .dischargeDate("23-01-2535 12:03:44")
                .age("0 ปี 3 เดือน")
                .admitDateTime("22-01-2535 12:03:44").build();


        nurseAdmitRepository.save(admit1);
        nurseAdmitRepository.save(admit2);
        nurseAdmitRepository.save(admit3);
        nurseAdmitRepository.save(admit4);
        nurseAdmitRepository.save(admit5);
        nurseAdmitRepository.save(admit6);

        nurseAdmitRepository.save(admit7);
        nurseAdmitRepository.save(admit8);
        nurseAdmitRepository.save(admit9);
        nurseAdmitRepository.save(admit10);
        nurseAdmitRepository.save(admit11);
        nurseAdmitRepository.save(admit12);
        nurseAdmitRepository.save(admit13);
        nurseAdmitRepository.save(admit14);
        nurseAdmitRepository.save(admit15);
        nurseAdmitRepository.save(admit16);
        nurseAdmitRepository.save(admit17);
        nurseAdmitRepository.save(admit18);
        nurseAdmitRepository.save(admit19);
        nurseAdmitRepository.save(admit20);

        nurseAdmitRepository.save(admit21);
        nurseAdmitRepository.save(admit22);
        nurseAdmitRepository.save(admit23);
        nurseAdmitRepository.save(admit24);
        nurseAdmitRepository.save(admit25);
        nurseAdmitRepository.save(admit26);
        nurseAdmitRepository.save(admit27);
        nurseAdmitRepository.save(admit28);
        nurseAdmitRepository.save(admit29);
        nurseAdmitRepository.save(admit30);


        nurseAdmitRepository.save(admit31);
        nurseAdmitRepository.save(admit32);
        nurseAdmitRepository.save(admit33);
        nurseAdmitRepository.save(admit34);
        nurseAdmitRepository.save(admit35);
        nurseAdmitRepository.save(admit36);

        nurseAdmitRepository.save(admit37);
        nurseAdmitRepository.save(admit38);
        nurseAdmitRepository.save(admit39);
        nurseAdmitRepository.save(admit40);
        nurseAdmitRepository.save(admit41);
        nurseAdmitRepository.save(admit42);
        nurseAdmitRepository.save(admit43);
        nurseAdmitRepository.save(admit44);
        nurseAdmitRepository.save(admit45);
        nurseAdmitRepository.save(admit46);
        nurseAdmitRepository.save(admit47);
        nurseAdmitRepository.save(admit48);
        nurseAdmitRepository.save(admit49);
        nurseAdmitRepository.save(admit50);
        nurseAdmitRepository.save(admit51);
        nurseAdmitRepository.save(admit52);
        nurseAdmitRepository.save(admit53);

        nurseAdmitRepository.save(admit54);
        nurseAdmitRepository.save(admit55);
        nurseAdmitRepository.save(admit56);

        VitalSign vitalSign = VitalSign.builder()
                //.dateOfBirth(formatter.parse("18-06-2023"))
                .heartRate(80)
                .respiratoryRate(20)
                .temperature(36.5)
                .oxygenSaturation(88)
                .oxygenTherapy(3)
                .systolic_blood_pressure(100)
                .diastolic_blood_pressure(70)
                //  .consciousness(Consciousness.A)
                .build();
        Triage triage = Triage.builder().build();
        PhysicalExam physicalExam = PhysicalExam.builder()
                .consciousness(Consciousness.A)
                .bounding_pulse(false)
                .weak_pulse(false)
                .cap_refill(false)
                .flash_cap(false)
                .build();
        RiskFactor riskFactor = RiskFactor.builder()
                .organtranplantation(false)
                .primary_immune_defencing(false)
                .postSplenectomy_asplenia(false)
                .malignancy(false)
                .bedRidden_cerebralPulsy(false)
                .center_iv_catheter(false)
                .suspected_infection(true)
                .build();
        InitialImpression initialImpression = InitialImpression.builder()
                .motting_skin(false)
                .petichea(false)
                .irritable(true)
                .stupor_drownsiness(true)
                .build();
        triage.setAdmit(admit1);
        triage.setInitialImpression(initialImpression);
        triage.setRiskFactor(riskFactor);
        triage.setPhysicalExam(physicalExam);
        triage.setVitalSign(vitalSign);
        MPEWBean mpew = new MPEWBean(triage);
        Sepsis sepsis = new Sepsis(triage);
        Shock shock = new Shock(triage);
        logger.info("heart rate : " + mpew.getHeartRateScore());
        logger.info("res : " + mpew.getRespiratoryRateScore());
        logger.info("temp : " + mpew.getTemperatureScore());
        logger.info("oxySar : " + mpew.getOxygenSaturationScore());
        logger.info("oxyTher : " + mpew.getOxygenTherapyScore());
        logger.info("con : " + mpew.getConsciousnessScore());
        logger.info("total : " + mpew.getTotalScore());
        logger.info("abnormal vitalsign : " + sepsis.getAbnormalVitalSign());
        logger.info("sepsis result: " + sepsis.getSepsisResult());
        logger.info("shock result: " + shock.getShockResult());

    }

    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_NURSE).build();
        user1 = User.builder()
                .username("nurse@gmail.com")
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
                .username("doctor@gmail.com")
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
                .username("admin@gmail.com")
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
