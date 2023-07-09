package enp.enp_backend.domain.admin.controller;

import enp.enp_backend.domain.admin.service.AdminService;
import enp.enp_backend.entity.Admit;
import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.Nurse;
import enp.enp_backend.entity.User;
import enp.enp_backend.security.entity.Authority;
import enp.enp_backend.security.entity.AuthorityName;
import enp.enp_backend.security.repository.AuthorityRepository;
import enp.enp_backend.security.util.JwtTokenUtil;
import enp.enp_backend.util.LabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    AdminService adminService;

    @GetMapping("admin/users")
    public ResponseEntity<?> getUserLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(adminService.getAllUser()));
    }

    @PostMapping("admin/users/disable/{id}")
    public ResponseEntity<?> disableUser(@PathVariable("id") Long id) {
        User output = adminService.getUser(id);
        output.setEnabled(false);
        adminService.save(output);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @GetMapping("admin/users/search")
    public ResponseEntity<?> getSearchedUser(@RequestParam(value = "_search", required = false) String search) throws JSONException {
        List<User> output = adminService.getSearchedUser(search);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given AN is not found");
        }
    }
/*

    @GetMapping("admin/users/doctors")
    public ResponseEntity<?> getDoctorUserLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(adminService.getDoctorUser()));
    }

    @GetMapping("admin/users/nurses")
    public ResponseEntity<?> getNurseUserLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(adminService.getNurseUser()));
    }
*/

    @PostMapping("/admin/register/nurse")
    public ResponseEntity<?> registerNurse(@RequestBody String json) throws AuthenticationException, JSONException {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authNurse = Authority.builder().name(AuthorityName.ROLE_NURSE).build();
        authorityRepository.save(authNurse);
        JSONObject jsonObject = new JSONObject(json);
        User regUser = User.builder()
                .enabled(true)
                // .email(jsonObject.get("email").toString())
                .username(jsonObject.get("email").toString())
                .password(encoder.encode(jsonObject.get("password").toString()))
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        regUser.getAuthorities().add(authNurse);

        Nurse regNurse = Nurse.builder()
                .name(jsonObject.get("name").toString())
                .surname(jsonObject.get("surname").toString())
                .phoneNumber(jsonObject.get("phoneNumber").toString())
                .medicalID(jsonObject.get("medicalID").toString())
                .gender(jsonObject.get("gender").toString())
                .build();

        adminService.save(regNurse);
        adminService.save(regUser);

        regUser.setNurse(regNurse);
        regNurse.setUser(regUser);

        adminService.save(regNurse);
        adminService.save(regUser);

        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(regUser));
    }


    @PostMapping("/admin/register/doctor")
    public ResponseEntity<?> registerDoctor(@RequestBody String json) throws AuthenticationException, JSONException {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        authorityRepository.save(authDoctor);
        JSONObject jsonObject = new JSONObject(json);
        User regUser = User.builder()
                .enabled(true)
                // .email(jsonObject.get("email").toString())
                .username(jsonObject.get("email").toString())
                .password(encoder.encode(jsonObject.get("password").toString()))
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        regUser.getAuthorities().add(authDoctor);

        Doctor regDoctor = Doctor.builder()
                .name(jsonObject.get("name").toString())
                .surname(jsonObject.get("surname").toString())
                .phoneNumber(jsonObject.get("phoneNumber").toString())
                .medicalID(jsonObject.get("medicalID").toString())
                .gender(jsonObject.get("gender").toString())
                .build();

        adminService.save(regDoctor);
        //userRepository.save(regUser);
        adminService.save(regUser);

        regUser.setDoctor(regDoctor);
        regDoctor.setUser(regUser);

        adminService.save(regDoctor);
        //userRepository.save(regUser);
        adminService.save(regUser);


        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(regUser));
    }


}
