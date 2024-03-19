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
//                .requestMatchers("/","/login", "/signup","/signup-process", "/perform_login").permitAll()
////                this line should fix lab
//                .requestMatchers(HttpMethod.POST, "/signup/save", "/perform_login").permitAll()
//                .requestMatchers("/collections", "/flashcard", "delete/**").authenticated()
////                .requestMatchers(HttpMethod.GET, "/flashcard", "/collections", "/", "delete/**").permitAll()
//                .requestMatchers("/include/**", "/css/**", "/icons/**", "/images/**", "/js/**", "/layer/**").permitAll()
                                .requestMatchers("**").permitAll() // TODO: remove this eventually
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/perform_login")
                                .successForwardUrl("/home") // required for thymeleaf security extras
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
