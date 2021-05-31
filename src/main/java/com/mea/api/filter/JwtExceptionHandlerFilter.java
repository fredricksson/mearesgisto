package com.mea.api.filter;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class JwtExceptionHandlerFilter extends OncePerRequestFilter {

	private final HandlerExceptionResolver handlerExceptionResolver;

	public JwtExceptionHandlerFilter(HandlerExceptionResolver handlerExceptionResolver) {
		this.handlerExceptionResolver = handlerExceptionResolver;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			filterChain.doFilter(request, response);
		} catch (RuntimeException ex) {
			handlerExceptionResolver.resolveException(request, response, null, ex);
		}

	}

}