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
import enp.enp_backend.entity.User;
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
import java.util.Optional;

@Controller
public class UserController {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    UserService userService;
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("currentUser")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String[] parts = token.split(" ");
        logger.info("parts :" + parts[1]);
        String finalToken = parts[1];
        logger.info("token :" + finalToken);
        String username = jwtTokenUtil.getUsernameFromToken(finalToken);
        logger.info("user :" + username);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        logger.info("jwtuser :" + user.getId());
        User output = userService.getUser(user.getId());
        logger.info("user 2 :" + output);

        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(output));
    }

}
