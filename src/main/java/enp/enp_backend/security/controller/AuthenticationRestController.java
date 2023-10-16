package enp.enp_backend.security.controller;


import enp.enp_backend.domain.doctor.service.DoctorService;
import enp.enp_backend.domain.nurse.service.NurseService;
import enp.enp_backend.security.entity.JwtUser;
import enp.enp_backend.entity.User;
import enp.enp_backend.security.repository.AuthorityRepository;
import enp.enp_backend.domain.user.repository.jpa.UserRepository;
import enp.enp_backend.domain.user.service.UserService;
import enp.enp_backend.security.util.JwtTokenUtil;
import enp.enp_backend.util.LabMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationRestController {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    UserRepository userRepository;
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


}
