package enp.enp_backend.domain.nurse.service;

import enp.enp_backend.domain.nurse.repository.jpa.*;
import enp.enp_backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseServiceImpl implements NurseService {
    @Autowired
    NurseRepository nurseRepository;

    @Autowired
    Nurse_PatientRepository nursePatientRepository;

    @Autowired
    Nurse_AdmitRepository nurseAdmitRepository;

    @Autowired
    BedRepository bedRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    Nurse_DoctorRepository nurseDoctorRepository;

    @Autowired
    Nurse_TriageRepository nurseTriageRepository;

    //-----------------Nurse---------

    @Override
    public Nurse save(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    //------------Patient-----------
   /* @Override
    public Patient getPatient(Long id) {
        return nursePatientRepository.findById(id).orElse(null);
    }
*/
    @Override
    public Patient save(Patient patient) {
        return nursePatientRepository.save(patient);
    }

    ;

    @Override
    public List<Patient> getAllpatient() {
        return nursePatientRepository.findAll(Pageable.unpaged()).getContent();
    }

    ;

    /*@Override
    public Patient getPatientByHn(String hn) {
        return nursePatientRepository.findPatientByHn(hn);
    }
*/
    /*@Override
    public List<Patient> getSearchedPatient(String name, String surname, String hn) {
        return nursePatientRepository.findByNameIgnoreCaseContainingOrSurnameIgnoreCaseContainingOrHnIgnoreCaseContaining(name, surname, hn);
    }*/

    //--------------Admit----------------

    @Override
    public Admit getAdmit(Long id) {
        return nurseAdmitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Admit> getAllAdmit() {
        return nurseAdmitRepository.findAll(Pageable.unpaged()).getContent();
    }

    @Override
    public Admit save(Admit admit) {
        return nurseAdmitRepository.save(admit);
    }

    @Override
    public Admit getAdmitByAn(String an) {
        return nurseAdmitRepository.findAdmitByAn(an);
    }

    @Override
    public List<Admit> getSearchedAdmit(String name, String surname, String hn, String an) {
        return nurseAdmitRepository.findByPatientNameIgnoreCaseContainingOrPatientSurnameIgnoreCaseContainingOrPatientHnIgnoreCaseContainingOrAnIgnoreCaseContaining(name, surname, hn, an);
    }

    //--------------Room------------------
    @Override
    public Room getRoom(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll(Pageable.unpaged()).getContent();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    //--------------Bed--------------------

    @Override
    public Bed getBed(Long id) {
        return bedRepository.findById(id).orElse(null);
    }

    @Override
    public Bed save(Bed bed) {
        return bedRepository.save(bed);
    }

    //---------------Doctor---------------

    @Override
    public Doctor getDoctor(Long id) {
        return nurseDoctorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return nurseDoctorRepository.findAll(Pageable.unpaged()).getContent();
    }

    @Override
    public Doctor save(Doctor doctor) {
        return nurseDoctorRepository.save(doctor);
    }

    @Override
    public Triage save(Triage triage) {
        return nurseTriageRepository.save(triage);
    }

    @Override
    public List<Triage> getAllTriage() {
        return nurseTriageRepository.findAll(Pageable.unpaged()).getContent();
    }

}

