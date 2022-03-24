package com.howhow.websecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService accountUserDetailService() {
		return new AccountUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder bcryptoEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(accountUserDetailService())
				.passwordEncoder(bcryptoEncoder());
				
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http 	
		.csrf().disable()
			.authorizeRequests() 
					.antMatchers("/**")
					.permitAll()
					.antMatchers("/student/**").hasAuthority("Teacher,Student")
					.antMatchers("/course/**").hasAuthority("Admin")
					.anyRequest()
					.authenticated()
								.and()
									.formLogin()
									.loginPage("/login")
									.usernameParameter("Account")
									.defaultSuccessUrl("/", true)
									.permitAll()
									.and()
									.rememberMe()
										.key("aaaaaaabbcccccc_1122334455")
										.tokenValiditySeconds(12*24*60)
									.and()
										.logout()
										.deleteCookies("JSESSIONID","remember-me")
										.permitAll();
									
						
				
		
					 
		
//		http.authorizeRequests().antMatchers("/*").permitAll();
//		.antMatchers("/users/**","/product/**","/brand/**","/settings/**","/login/**").hasAuthority("Admin")
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//			.loginPage("/login")
//			.usernameParameter("email")
//			.permitAll()
//			.and().logout().permitAll()
//			.and().rememberMe()
//					.key("aaaaaaabbcccccc_1122334455")
//					.tokenValiditySeconds(12*24*60);
//		
//		http.authorizeRequests().anyRequest().permitAll(); .and().formLogin().disable();
	}

	@Override
	public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web)
			throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/images/**","/js/**","/webjars/**","/course-photos/**","/assets/**","/static/**",  "/css/**", "/img/**", "/json/**");

	}



	
}
