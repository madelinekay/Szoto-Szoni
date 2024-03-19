package com.Undis.Madeline.SzotoSzoves_CaseStudy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/","/login", "/signup","/signup-process").permitAll()
//                this line should fix lab
                .requestMatchers(HttpMethod.POST, "/signup/save", "/login", "/flashcard").permitAll()
                .requestMatchers("/collections", "/flashcard", "/").authenticated()
                .requestMatchers(HttpMethod.GET, "/flashcard", "/collections", "/").permitAll()
                .requestMatchers("/include/**", "/css/**", "/icons/**", "/images/**", "/js/**", "/layer/**", "/static/**").permitAll()
        )
//might have worked with just "/static"
               .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
               .successForwardUrl("/flashcard") // required for thymeleaf security extras
//                       .defaultSuccessUrl("/flashcard", true)
                .permitAll()
        )
                .logout(
                        logout -> logout
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }
}
