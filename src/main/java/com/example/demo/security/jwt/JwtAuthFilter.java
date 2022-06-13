package com.example.demo.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.services.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");	
		System.out.println(requestTokenHeader);
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
			try {
				//Extract username from token
				
				jwtToken = requestTokenHeader.substring(7);
				username = this.jwtUtils.extractUsername(jwtToken);
			}catch(ExpiredJwtException e){
				e.printStackTrace();
				System.out.println("Token has expired !!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			//Later implement with logger
			System.out.println("Invalid Token");
		}
		
		//Validate token 
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
						
			if(this.jwtUtils.validateToken(jwtToken, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}else {
			System.out.println("Invalid Token");
		}
		
		filterChain.doFilter(request, response);
	}
}
