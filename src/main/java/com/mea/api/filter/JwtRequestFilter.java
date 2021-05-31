package com.mea.api.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mea.api.misc.ApiResponseObject;
import com.mea.api.security.service.MyUserDetailsService;
import com.mea.api.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
	ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
    	try {
    		 final String authorizationHeader = request.getHeader("Authorization");

    	        String username = null;
    	        String jwt = null;

    	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
    	            jwt = authorizationHeader.substring(7);
    	            username = jwtUtil.extractUsername(jwt);
    	        }


    	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

    	            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

    	            if (jwtUtil.validateToken(jwt, userDetails)) {

    	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
    	                        userDetails, null, userDetails.getAuthorities());
    	                usernamePasswordAuthenticationToken
    	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    	            }
    	        }
    	        chain.doFilter(request, response);
		} catch (Exception e) {
			String message = "Você não está permitido a acessar este recurso. Por favor autentique-se !";

			Map<String, Object> data = new ApiResponseObject().response(Boolean.TRUE, message, Arrays.asList());

			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			response.getWriter().write(objectMapper.writeValueAsString(data));
		}
       
    }
	
	
    
}
