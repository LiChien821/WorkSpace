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
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

import com.howhow.entity.UserAccountDt;


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
//	@Bean
	//public ClientRegistrationRepository clientRegistrationRepository() {
//		return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
//	}
//	private ClientRegistration googleClientRegistration() {
	//	return ClientRegistration.withRegistrationId("google")
	//		.clientId("927386807388-g1edo9vurckkbou7pe0v06bm5bg001pa.apps.googleusercontent.com")
	//		.clientSecret("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")
	//		.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
	//		//.redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
	//		.scope("openid", "profile", "email", "address", "phone")
		//	.authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
		// 	.tokenUri("https://www.googleapis.com/oauth2/v4/token")
		//	.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
	//		.userNameAttributeName(IdTokenClaimNames.SUB)
		//	.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
	//		.clientName("Google Login")
	//		.build();
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(accountUserDetailService())
				.passwordEncoder(bcryptoEncoder());
		
				
	}
	

    

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		  http  
		  .csrf().disable()
		   .authorizeRequests() 
		     .antMatchers("/teacherPage/**")
		   	 .authenticated()
		     .antMatchers("/login.html","/login","/register","/createUser","/verify","/css/","/courses/**","/product","/api/**","/shopping/**")
		     .permitAll()
		     .antMatchers("/student/**").hasAnyAuthority("Teacher","Student")
		     .antMatchers("/course/**").hasAnyAuthority("Admin","Admin")
		     .antMatchers("/api/mycourse").hasAnyAuthority("Teacher","Student")
		     .antMatchers("/login.html","/login","/register","/createUser","/verify","/css")
		     .permitAll()
		     .antMatchers("/student/**").hasAnyAuthority("Teacher","Student")
		     .antMatchers("/course/**").hasAnyAuthority("Admin","SuperAdmin")
		     .antMatchers("/cms/**").hasAnyAuthority("Admin","SuperAdmin")

		     .anyRequest()
		     .authenticated()
		        .and()
		         .formLogin()
		         .loginPage("/login")
		         .usernameParameter("account")
		         .defaultSuccessUrl("/home", true)
		         .permitAll()
		         .and()
		         .oauth2Login()
		         .loginPage("/login")
		         .defaultSuccessUrl("/google_register",true)
		         .permitAll()
		         .and()
		         .rememberMe()
		          .key("aaaaaaabbcccccc_1122334455")
		          .tokenValiditySeconds(12*24*60)
		         .and()
		          .logout()
		          .logoutSuccessUrl("/")
		          .deleteCookies("JSESSIONID","remember-me")
		          .permitAll();
		  
									
	}

	@Override
	public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web)
			throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/image/**","/images/**","/js/**","/webjars/**","/course-photos/**","/assets/**","/static/**",  "/css/**", "/img/**", "/json/**");

	}



	
}