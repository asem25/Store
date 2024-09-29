package ru.semavin.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.semavin.app.services.PersonDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfig {
    private final PersonDetailsService personDetailsService;
    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> {
                auth
                        .requestMatchers("/","/login/**", "/error").permitAll()
                        .anyRequest().hasAnyRole("USER", "ADMIN");
            })
                .formLogin(form -> form
                        .loginPage("/login/page")       // URL страницы логина
                        .loginProcessingUrl("/process_login")  // URL, на который отправляется форма логина
                        .defaultSuccessUrl("/", true)  // URL по умолчанию после успешного логина
                        .failureUrl("/login/page?error")  // URL в случае неудачного логина
                        .usernameParameter("username")  // Имя поля формы для имени пользователя
                        .passwordParameter("password")  // Имя поля формы для пароля
                )
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                                .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                        )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(personDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}