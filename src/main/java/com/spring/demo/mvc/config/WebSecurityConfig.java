package com.spring.demo.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;
 
  @Autowired
  PasswordEncoder passwordEncoder;
  
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  };
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	  auth.inMemoryAuthentication()
//      .passwordEncoder(passwordEncoder)
//      .withUser("user").password(passwordEncoder.encode("123456")).roles("USER")
//      .and()
//      .withUser("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN");
 
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

	  
	  http
//    .authorizeRequests().antMatchers("/login**").permitAll()
//    .and()
//    .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
//    .and()
//    .logout().logoutSuccessUrl("/login").permitAll()
//    .and()
    .authorizeRequests()
//    .antMatchers("/**").hasAnyRole("ADMIN", "USER")
    .anyRequest().authenticated()
    .and()
    .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
    .and()
    .logout().logoutSuccessUrl("/login").permitAll()
//    .and()
//    .authorizeRequests()
//    .anyRequest().hasAnyRole("ADMIN", "USER")
    .and()
    .csrf().disable();
    

  }
}
