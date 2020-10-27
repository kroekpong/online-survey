package com.khotdee168.common.config;

import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	 @Value("${cas.server-url-prefix}") 
	 private String CAS_SERVICE;
	 
	 @Value("${cas.server-login-url}") 
	 private String CAS_SERVICE_LOGIN;
	 
	 
	@Bean
	public ServiceProperties serviceProperties() {
	 ServiceProperties serviceProperties = new ServiceProperties();
	 serviceProperties.setService(CAS_SERVICE);
	 serviceProperties.setSendRenew(false);
	 return serviceProperties;
	}
	 
	@Bean
	@Primary
	public AuthenticationEntryPoint authenticationEntryPoint(
	 ServiceProperties sP) {
	 
	 CasAuthenticationEntryPoint entryPoint
	 = new CasAuthenticationEntryPoint();
	 entryPoint.setLoginUrl(CAS_SERVICE_LOGIN);
	 entryPoint.setServiceProperties(sP);
	 return entryPoint;
	}
	 
	@Bean
	public TicketValidator ticketValidator() {
		return new Cas30ServiceTicketValidator(CAS_SERVICE);
	}
	 
	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
	 
	 CasAuthenticationProvider provider = new CasAuthenticationProvider();
	 provider.setServiceProperties(serviceProperties());
	 provider.setTicketValidator(ticketValidator());
	 provider.setUserDetailsService( s -> new User("username", "password", true, true, true, true,
	 AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
	 provider.setKey("CAS_PROVIDER_LOCALHOST_9000");
	 return provider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.regexMatchers("/cassecured.*", "/login")
		.authenticated()
		.and()
		.authorizeRequests()
		.regexMatchers("/")
		.permitAll()
		.and()
		.httpBasic()
		.authenticationEntryPoint(authenticationEntryPoint(serviceProperties()));
	}
	
	
	
	
/*
	@Autowired
    private CustomAuthenticationProvider authProvider;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/", "/login/**","/resources/**","/assets/**",
				"/rest/**","/vote/**","/register/**","/v/**","/cancel/**","/ws/**"
				,"/question/getQuestionView/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/home")
			.failureUrl("/login?error")
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/maintenance")
        .and().logout()
			.invalidateHttpSession(true)
	        .permitAll();
		//TODO iframe
		
		http.headers().frameOptions().disable();

	}

	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }*/
	
	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception { 
		final String sqlUserName = "select user_id,password,enabled  from tb_user  where user_id = ? " ;
	    final String sqlAuthorities = "select user_id,authority from tb_user_role where user_id = ?";
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery(sqlUserName)
			.authoritiesByUsernameQuery(sqlAuthorities)
			.passwordEncoder(passwordEncoder());
	}*/
	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("1234").authorities("ROLE_ADMIN");
		
//        auth.get
//		 RestTemplate rest = new RestTemplate();
//		    
//		 Map user = rest.getForObject(LOGIN_API, Map.class);
//		 
//		 System.out.println("user : "+user);
//    }
	
//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    	
//    	auth.get
//        auth.userDetailsService(userDetailsService);
//    }
	    
}
