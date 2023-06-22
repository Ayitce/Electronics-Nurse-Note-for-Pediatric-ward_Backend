package enp.enp_backend.security.config;


import enp.enp_backend.security.controller.JwtAuthenticationEntryPoint;
import enp.enp_backend.security.controller.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {

    @Autowired
    private final JwtAuthenticationTokenFilter tokenFilter;
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/auth/**", "/register", "/registerDoctor", "/registerNurse").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/refresh", "/nurse/**", "/currentUser").hasRole("NURSE")
                        .requestMatchers(HttpMethod.GET, "/auth/refresh", "/doctor/**", "/currentUser").hasRole("DOCTOR")
                        .requestMatchers("/nurse/**").hasRole("NURSE")
                        .requestMatchers("/doctor/**").hasRole("DOCTOR")

                        //  .requestMatchers(HttpMethod.GET,"/**").hasRole("DOCTOR")
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        log.info("security filter chain set");
        return http.build();
    }


    @Bean
    ServerHttpSecurity serverHttpSecurity() {

        return ServerHttpSecurity.http();
    }


    @SneakyThrows
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }


    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }


}