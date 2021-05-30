package com.mea.api.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mea.api.misc.ApiResponseObject;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException)

			throws IOException, ServletException {
		String message = "Você não está permitido a acessar este recurso. Por favor autentique-se!";

		Map<String, Object> data = new ApiResponseObject().response(Boolean.TRUE, message, Arrays.asList());

		res.setContentType("application/json;charset=UTF-8");
		res.setStatus(200);
		res.getWriter().write(objectMapper.writeValueAsString(data));

	}
}