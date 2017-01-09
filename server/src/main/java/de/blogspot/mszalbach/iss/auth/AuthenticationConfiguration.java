package de.blogspot.mszalbach.iss.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration who can access which resource.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthenticationConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/api/session").permitAll()
            .antMatchers("/api/securities/**").authenticated()
            .and()
            .httpBasic()
            .and()
            .logout()
            .permitAll()
            .and()
            .sessionManagement().and()
            .csrf().disable();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("Ralf").password("ralf").roles("EMITTENT")
            .and()
            .withUser("Daniel").password("daniel").roles("REVISION")
            .and()
            .withUser("Marcel").password("marcel").roles("ADMIN");
    }

}
