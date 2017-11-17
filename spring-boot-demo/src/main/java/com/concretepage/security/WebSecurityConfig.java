package com.concretepage.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.concretepage.service.HRUserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private HRUserDetailsServiceImpl userDetails;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		log.info("Authorizing the request patterns");
		//http.authorizeRequests().anyRequest().fullyAuthenticated();
		http.authorizeRequests().antMatchers("/**").authenticated().and().rememberMe();
		//http.authorizeRequests().antMatchers("/send-pin").permitAll();
		//http.authorizeRequests().antMatchers("/check-pin").permitAll();
		//http.authorizeRequests().and().formLogin().and().rememberMe();
		http.httpBasic();
		http.csrf().disable();
		//http.csrf().csrfTokenRepository(csrfTokenRepository());
		http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
		http.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                //return new CorsConfiguration().applyPermitDefaultValues();
            	CorsConfiguration config = new CorsConfiguration();
            	//config.applyPermitDefaultValues();
            	config.setAllowCredentials(true);
        		config.addAllowedOrigin("http://localhost/*");
        		config.addAllowedHeader("*");
        		config.addAllowedMethod("*");
            	  	return config;
            }
        });
		
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails);
    }
	
	private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        log.info("Creating the HttpSessionCsrfTokenRepository");
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
	
	

}
