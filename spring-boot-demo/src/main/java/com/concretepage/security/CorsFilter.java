package com.concretepage.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@Component
public class CorsFilter extends OncePerRequestFilter {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token , X-CSRFToken");
        response.addHeader("Access-Control-Expose-Headers", "xsrf-token, authorization");
        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
          String headerName = (String)headerNames.nextElement();
          log.info("Header Name : " + headerName + " value :  " + request.getHeader(headerName));
          
        }
        String authValue = request.getHeader("authorization");
        response.setHeader("authorization", authValue);
        log.info("authorization header value is " + authValue);
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
        	String token = csrf.getToken();
        	
        	String jsessionId = request.getHeader("JSESSIONID");
        	log.info("Setting response header for XSRF-TOKEN" + token + " the authorization value is " + authValue + " and Jession is " + jsessionId);
        	response.setHeader("XSRF-TOKEN", csrf.getToken());
        	
        	//response.setHeader("JSESSIONID", jsessionId);
            Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
            if (cookie==null || token!=null && !token.equals(cookie.getValue())) {
                cookie = new Cookie("XSRF-TOKEN", token);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        } 
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            if(csrf != null) {
               	 response.setHeader("xsrf-token", csrf.getToken());
            }
           
        } else { 
            filterChain.doFilter(request, response);
        }
        
       
    }
}
