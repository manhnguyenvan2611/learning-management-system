package com.learningmanagementsystem.learning_management_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {                
        http.csrf(customizer -> customizer.disable()).
            authorizeHttpRequests(request -> request
                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                .requestMatchers("/css/**").permitAll()                 
                .requestMatchers("/").permitAll()         
                .requestMatchers("/signup").permitAll()         
                .requestMatchers("/login").permitAll()                 
                .requestMatchers("/assets/**").permitAll()                 
                .requestMatchers("/js/**").permitAll()                 
                .requestMatchers("/registeruser").permitAll()                 
                .requestMatchers("/sendemail").permitAll()                 
                .requestMatchers("/classes").hasRole("LECTURER")                           
                .anyRequest().authenticated()
            );
      
        http
		.formLogin((form) -> form
			.loginPage("/login")
            .usernameParameter("email")
            .defaultSuccessUrl("/", true)
            .permitAll());

        http
            .logout((logout) -> logout
            .logoutSuccessUrl("/")
            .permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
}
