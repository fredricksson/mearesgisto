package com.mea.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mea.api.error.ResourceNotFoundException;
import com.mea.api.misc.ApiResponseObject;
import com.mea.api.model.JwtRequest;
import com.mea.api.model.JwtResponse;
import com.mea.api.security.service.MyUserDetailsService;
import com.mea.api.util.JwtUtil;

@RestController
@RequestMapping("/api/v1")
public class AuthenticateController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;
	@CrossOrigin
	@PostMapping(value = "/authenticate")
	public ResponseEntity<Map<String, Object>> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			String message = "utilizador/senha inválida!";
			throw new ResourceNotFoundException(message);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		Map<String, Object> apiResponse = new ApiResponseObject().response(Boolean.FALSE, "Autenticado com successo!",
				new JwtResponse(jwt));

		return ResponseEntity.ok(apiResponse);
	}

}