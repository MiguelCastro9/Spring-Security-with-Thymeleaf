package com.permissao.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Miguel Castro
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceConfig();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    protected WebSecurityCustomizer webSecurityCustomizer(AuthenticationManagerBuilder auth) throws Exception {

        return (web) -> auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyAuthority("CRIADOR", "VISUALIZADOR")
                .antMatchers("/new").hasAnyAuthority("CRIADOR")
                .antMatchers("/edit/**").hasAnyAuthority("CRIADOR")
                .antMatchers("/delete/**").hasAuthority("CRIADOR")
                .antMatchers("/cadastro").permitAll()
                .antMatchers("/save").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
}
