package com.test.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class UserSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	/*
	 * this method to configure WebSecurity
	 * 
	 * @param web
	 * 
	 * @Throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	/*
	 * Override this method to configure the HttpSecurity.
	 * 
	 * @paramhttp the HttpSecurity to modify
	 * 
	 * @Throws:Exception - if an error occurs
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/expense/*").hasAnyRole("USER").anyRequest()
				.authenticated().and().formLogin().permitAll();
		//Cross-site request forgery disabled
		http.csrf().disable();
	}

	/*
	 * the method authenticate and authorize the user
	 * 
	 * @param authenticationMgr used to create an AuthenticationManager. Allows for
	 * easily building different types of authentication
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.inMemoryAuthentication().withUser("user").password(new BCryptPasswordEncoder().encode("user"))
				.authorities("ROLE_USER");
	}


}