package com.app.ecommerce.shopping.config;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/","/login","/product", "/register", "/css/**").permitAll() // Public pages
                .anyRequest().authenticated() // Everything else requires login
            )
            .formLogin(form -> form
                .loginPage("/login")             // Your custom login GET mapping
                .defaultSuccessUrl("/index", true) // WHERE TO GO AFTER LOGIN
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}
*/