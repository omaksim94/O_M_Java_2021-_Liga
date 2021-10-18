package com.socialnetwork.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService MyUserDetailsService;
    private final JwtConfig jwtConfig;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(MyUserDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig))
                .addFilterAfter(new JwtTokenVerifier(MyUserDetailsService, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/v1/people").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/**").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated();
    }

    @Bean
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
