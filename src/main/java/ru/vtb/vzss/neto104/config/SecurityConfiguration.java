package ru.vtb.vzss.neto104.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(
        securedEnabled = true, prePostEnabled = true, jsr250Enabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("READ", "WRITE", "DELETE")
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .roles("READ", "WRITE")
                .and()
                .withUser("Ivan")
                .password(passwordEncoder().encode("pass"))
                .authorities("READ");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers("/person/hello").permitAll()
                .and()
                .authorizeRequests().antMatchers("/person/by-city").authenticated()
                .and()
                .authorizeRequests().antMatchers("/person/by-name").authenticated()
                .and()
                .authorizeRequests().antMatchers("/person/less-than").hasAuthority("admin")
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
