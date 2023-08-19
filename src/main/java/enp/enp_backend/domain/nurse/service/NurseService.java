package enp.enp_backend.domain.nurse.service;

import enp.enp_backend.entity.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NurseService {

    //-----------Nurse---------
    Nurse save(Nurse nurse);

    //---------------Patient----------------


    /*Patient getPatient(Long id);*/

    Patient save(Patient patient);

    List<Patient> getAllpatient();

//    Patient getPatientByHn(String hn);
//
//    List<Patient> getSearchedPatient(String name,String surname, String hn);

    //--------------Admit------------------

    Admit getAdmit(Long id);

    List<Admit> getAllAdmit();

    Admit save(Admit admit);

    Admit getAdmitByAn(String an);

    List<Admit> getSearchedAdmit(String name, String surname, String hn, String an);

    //--------------Room------------------
    Room getRoom(Long id);

    List<Room> getAllRoom();

    Room save(Room room);

    //--------------Bed--------------------
    Bed getBed(Long id);

    Bed save(Bed bed);

    //--------------Doctor-------------------

    Doctor getDoctor(Long id);

    List<Doctor> getAllDoctor();

    Doctor save(Doctor doctor);

    //Triage
    Triage save(Triage triage);

    List<Triage> getAllTriage();

}
