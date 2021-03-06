package pesiykot.memorius.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private DataSource dataSource;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers()
                .frameOptions().disable().and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/about", "/registration", "/h2/**").permitAll()
                    .antMatchers("/admin/**").access("hasRole('ADMIN')")
                    .antMatchers("/user/**").hasAnyRole("USER")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .successHandler(authenticationSuccessHandler)
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email, password, 'true' as enabled from user_account where email=? limit 1")
                .authoritiesByUsernameQuery("select u.email, r.name from users_roles as ur inner join user_account as u on u.id = ur.user_id inner join role as r on ur.role_id = r.id where u.email=?");
    }
}
