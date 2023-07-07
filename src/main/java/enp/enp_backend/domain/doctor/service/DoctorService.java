package enp.enp_backend.domain.doctor.service;

import enp.enp_backend.entity.Admit;
import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DoctorService {

    Doctor save(Doctor doctor);

    //-----------Admit------------------

    List<Admit> getAllAdmit();

    Admit getAdmitByAn(String an);

    List<Admit> getSearchedAdmit(String name, String surname, String hn, String an);

}
