package com.bom.config;

import com.bom.security.JWTAuthenticationFilter;
import com.bom.security.JWTAuthorizationFilter;
import com.bom.security.JwtUtil;
import com.bom.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;


@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager, JwtUtil jwtUtil) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .addFilterBefore(new JWTAuthenticationFilter(authenticationManager, jwtUtil), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JWTAuthorizationFilter(authenticationManager, jwtUtil), UsernamePasswordAuthenticationFilter.class); // ✅ Corrected
//
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll() // Allow all requests
//                );
//
//        return http.build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/h2-console/**") // Disable CSRF for H2 Console
            )
            .headers(headers -> headers
                    .frameOptions(frame -> frame.disable()) // Allow frames for H2 Console
            )
            .formLogin(Customizer.withDefaults());

    return http.build();
}


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
