package poo.project.security;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import poo.project.security.Service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailsServiceImpl userDetailsServiceImpl;
    private PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().authenticated()
//                )
//                .formLogin()
//                .and()
//                .userDetailsService(userDetailsServiceImpl);
        http.csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().permitAll();
        return http.build();
    }

}
