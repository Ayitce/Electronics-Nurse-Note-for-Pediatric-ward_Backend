package enp.enp_backend.security.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.Nurse;
import enp.enp_backend.repository.DoctorRepository;
import enp.enp_backend.repository.NurseRepository;
import enp.enp_backend.security.entity.Authority;
import enp.enp_backend.security.entity.AuthorityName;
import enp.enp_backend.security.entity.JwtUser;
import enp.enp_backend.security.entity.User;
import enp.enp_backend.security.repository.AuthorityRepository;
import enp.enp_backend.security.repository.UserRepository;
import enp.enp_backend.security.service.UserService;
import enp.enp_backend.security.util.JwtTokenUtil;
import enp.enp_backend.service.DoctorService;
import enp.enp_backend.service.NurseService;
import enp.enp_backend.util.LabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationRestController {

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

    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        Map result = new HashMap();
        result.put("token", token);
        User user = userRepository.findById(((JwtUser) userDetails).getId()).orElse(null);
        if (user.getNurse() != null) {
            result.put("user", LabMapper.INSTANCE.getNurseAuthDTO(user.getNurse()));
        } else if (user.getDoctor() != null) {
            result.put("user", LabMapper.INSTANCE.getDoctorAuthDTO(user.getDoctor()));
        } else {
            result.put("user", LabMapper.INSTANCE.getUserAuthDTO(user));
        }

        return ResponseEntity.ok(result);
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws AuthenticationException {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        authorityRepository.save(authUser);
        User regUser = User.builder()
                .enabled(true)
                //.email(user.getEmail())
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        regUser.getAuthorities().add(authUser);
        //userRepository.save(regUser);

        userService.save(regUser);
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(regUser));
    }


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
                .nurseID(jsonObject.get("medicalID").toString())
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
                .doctorID(jsonObject.get("medicalID").toString())
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

    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
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
