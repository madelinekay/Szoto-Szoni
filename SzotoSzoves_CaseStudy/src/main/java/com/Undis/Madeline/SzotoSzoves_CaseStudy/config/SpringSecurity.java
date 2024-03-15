package com.Undis.Madeline.SzotoSzoves_CaseStudy.config;

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
        http.csrf((csrf) -> csrf.disable());
//        .authorizeHttpRequests((auth) -> auth
//                sites you can access without authorization
//                .requestMatchers("/index", "/signup","/signup-process").permitAll()
//                .requestMatchers("/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**", "/static/**").permitAll()
//                .requestMatchers(HttpMethod.POST, "/signup/save").permitAll()
////                pages for authenticated users
////                to-do: rename login page
//                .requestMatchers("/collections", "/flashcard").authenticated())
//                       .formLogin(form -> form
//                        .loginPage("/index")
//                        .loginProcessingUrl("/index")
//                        .successForwardUrl("/flashcard") // required for thymeleaf security extras
//                        .permitAll()
//                )
//                .logout(
//                        logout -> logout
//                                .invalidateHttpSession(true)
//                                .clearAuthentication(true)
//        //                                I think this code just checks that logout is in the url pattern
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .permitAll()
//                );
        return http.build();
    }
}
