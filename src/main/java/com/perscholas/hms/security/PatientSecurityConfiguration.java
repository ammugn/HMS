package com.perscholas.hms.security;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Ammu Nair
 */
@Slf4j
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Order(2)
public class PatientSecurityConfiguration extends WebSecurityConfigurerAdapter {
    PatientUserDetailsService patientUserDetailsService;
    AuthenticationSuccessHandler successHandler;

    @Autowired
    public PatientSecurityConfiguration(PatientUserDetailsService patientUserDetailsService , AuthenticationSuccessHandler successHandler) {
        this.patientUserDetailsService = patientUserDetailsService;
        this.successHandler = successHandler;
    }

    @Bean
    public BCryptPasswordEncoder patientPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider patientauthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(patientUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(patientPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AccessDeniedHandler patientaccessDeniedHandler() {
        return new AppAccessDeniedHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(patientauthenticationProvider());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/assets/**", "/images/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        log.info("In patient security configuration");

       /* http.
                csrf().disable().
                authorizeRequests().
                antMatchers("/", "/index", "/medihealth", "/meditech","/meditech/registerAdminOrDoctor","/medihealth/registerPatient","/medihealth/login","/meditech/saveUser","/medihealth/patientDashboard","/medihealth/logout").permitAll().
                antMatchers("/medihealth/logout","/medihealth/**").hasAuthority("ROLE_PATIENT").
                anyRequest().authenticated().
                and().
                formLogin().loginPage("/medihealth/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/medihealth/patientDashboard")
                .defaultSuccessUrl("/medihealth/patientDashboard", true).
                failureUrl("/medihealth/patientDashboard?error=true").permitAll().
                and().
                logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/medihealth/logout")).
                logoutSuccessUrl("/medihealth/login").permitAll().and().exceptionHandling().accessDeniedPage("/accessdenied");
*/

       /* http
                .authorizeRequests().
               // antMatchers( "/medihealth","/medihealth/login","/medihealth/savePatient","/medihealth/patientDashboard").permitAll();
                       antMatchers( "/medihealth/**").permitAll();

                       http
                .authorizeRequests().
                antMatchers("/medihealth/patientDashboard","medihealth/viewAppointments/*").hasAuthority("ROLE_PATIENT").
                anyRequest().authenticated()
                .and().formLogin().loginPage("/medihealth/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/medihealth/login")
                .defaultSuccessUrl("/medihealth/patientDashboard", true)
                .failureUrl("/medihealth/patientDashboard?error=true")
                .permitAll()
                .and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("medihealth/logout")).
                logoutSuccessUrl("/medihealth/login").permitAll().and().exceptionHandling().accessDeniedPage("/accessdenied");
        http.csrf().disable();
*/

        http
                .antMatcher("/medihealth/bookAppointment/*")
                .authorizeRequests().anyRequest().authenticated()
                .and().formLogin().loginPage("/medihealth/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/medihealth/patientDashboard")
                .defaultSuccessUrl("/medihealth/patientDashboard", true)
                .failureUrl("/medihealth/patientDashboard?error=true")
                .permitAll()
                .and().logout().logoutSuccessUrl("/medihealth/login");

        http.csrf().disable();

    }
}
