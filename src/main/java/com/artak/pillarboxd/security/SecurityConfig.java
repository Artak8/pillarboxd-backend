package com.artak.pillarboxd.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    /*
        Configure the security filter, we only allow unauthenticated clients to access limited parts of the backend.

        If no account is used, the frontend will log-in using the guest user we create.
        If an account is registered, the frontend will log-in using the same account.
    */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/site.webmanifest",
                                "/ping",
                                "/reset-password").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

    //Create a password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Create the guest account
    @Bean
    UserDetailsService userDetailsService() {
        String encodedPassword = passwordEncoder().encode("password");

        UserDetails user = User
                .withUsername("guest")
                .password(encodedPassword)
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
