package com.perscholas.hms.security;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Ammu Nair
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    AppUserDetailsService appUserDetailsService;
    AuthenticationSuccessHandler successHandler;

    @Autowired
    public AppSecurityConfiguration(AppUserDetailsService appUserDetailsService, AuthenticationSuccessHandler successHandler) {
        this.appUserDetailsService = appUserDetailsService;
        this.successHandler = successHandler;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(appUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/assets/**", "/img/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                csrf().disable().
                authorizeRequests().
                antMatchers("/", "/index", "/medihealth", "/meditech", "/meditech/registerAdminOrDoctor","/medihealth/registerPatient","/medihealth/login","/meditech/saveUser").permitAll().
                antMatchers("/medihealth/patientDashboard","/medihealth/saveorupdateappointment","/medihealth/bookAppointment/**").hasAnyAuthority("ROLE_PATIENT").
                antMatchers("/patients","/dashboard","/patients/**","/doctors","/doctors/**").hasAnyAuthority("ROLE_ADMIN").
                antMatchers("/meditech/**","/dashboard").hasAnyAuthority("ROLE_DOCTOR").
                anyRequest().authenticated().
                and().
                formLogin().loginPage("/index").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/dashboard")
                .defaultSuccessUrl("/dashboard", true).
                failureUrl("/index?error=true").permitAll().
                and().
                logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
                logoutSuccessUrl("/").permitAll().and().exceptionHandling().accessDeniedPage("/accessdenied");





    }
}
