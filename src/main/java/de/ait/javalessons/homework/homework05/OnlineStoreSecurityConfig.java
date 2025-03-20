package de.ait.javalessons.homework.homework05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class OnlineStoreSecurityConfig {
    @Bean
    public PasswordEncoder onlineStorePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager onlineStoreUserDetailsManager(PasswordEncoder passwordEncoder) {
        UserDetails customer = User.withUsername("customer")
                .password(passwordEncoder.encode("customerpass"))
                .roles("CUSTOMER")
                .build();
        UserDetails manager = User.withUsername("manager")
                .password(passwordEncoder.encode("managerpass"))
                .roles("MANAGER")
                .build();
        return new InMemoryUserDetailsManager(customer, manager);
    }

    @Bean
    public SecurityFilterChain onlineStoreSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/products/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/products/public/list").permitAll()
                        .requestMatchers("/products/customer/cart").hasRole("CUSTOMER")
                        .requestMatchers("/products/manager/add").hasRole("MANAGER")
                        .requestMatchers("/products/**").authenticated()
                )
                .formLogin(withDefaults());
        return http.build();
    }
}
