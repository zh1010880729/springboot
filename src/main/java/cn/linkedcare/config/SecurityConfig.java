package cn.linkedcare.config;

import cn.linkedcare.filter.JwtAuthenticationFilter;
import cn.linkedcare.filter.JwtLoginFilter;
import cn.linkedcare.security.MyAccessDeniedHandler;
import cn.linkedcare.security.MyAuthenticationEntryPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Benji on 2018/5/9.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler())
                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/login", "/user/register", "/apollo/**", "/swagger-ui**", "/user/redis/topic").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
