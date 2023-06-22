package enp.enp_backend.domain.user.controller;

import enp.enp_backend.domain.doctor.service.DoctorService;
import enp.enp_backend.domain.nurse.service.NurseService;
import enp.enp_backend.domain.user.repository.jpa.UserRepository;
import enp.enp_backend.domain.user.service.UserService;
import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.Nurse;
import enp.enp_backend.security.entity.Authority;
import enp.enp_backend.security.entity.AuthorityName;
import enp.enp_backend.security.entity.JwtUser;
import enp.enp_backend.security.entity.User;
import enp.enp_backend.security.repository.AuthorityRepository;
import enp.enp_backend.security.util.JwtTokenUtil;
import enp.enp_backend.util.LabMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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

@Controller
public class UserController {
    private final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    NurseService nurseService;
    @Autowired
    DoctorService doctorService;
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register/nurse")
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
                .dateOfBirth(jsonObject.get("dateOfBirth").toString())
                .build();

        nurseService.save(regNurse);
        //userRepository.save(regUser);
        userService.save(regUser);

        regUser.setNurse(regNurse);
        regNurse.setUser(regUser);

        nurseService.save(regNurse);
        userService.save(regUser);

        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(regUser));
    }


    @PostMapping("/register/doctor")
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
                .dateOfBirth(jsonObject.get("dateOfBirth").toString())
                .build();

        doctorService.save(regDoctor);
        //userRepository.save(regUser);
        userService.save(regUser);

        regUser.setDoctor(regDoctor);
        regDoctor.setUser(regUser);

        doctorService.save(regDoctor);
        //userRepository.save(regUser);
        userService.save(regUser);


        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(regUser));
    }

    @GetMapping("currentUser")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String[] parts = token.split(" ");
        //logger.info("parts :" + parts[1]);
        String finalToken = parts[1];
        logger.info("token :" + finalToken);
        String username = jwtTokenUtil.getUsernameFromToken(finalToken);
        logger.info("user :" + username);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        User user2 = userRepository.findById((user).getId()).orElse(null);


        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(user2));
    }

    @GetMapping("user")
    public ResponseEntity<?> getUserLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<User> pageOutput;
        pageOutput = userService.getUsers(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();

        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getUserDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }


    @GetMapping("user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        User output = userService.getUser(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

}
