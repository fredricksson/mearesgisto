package com.mea.api.util;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mea.api.error.ResourceNotFoundException;
import com.mea.api.model.Register;
import com.mea.api.repository.RegisterRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JwtUtil {

	@Autowired
	RegisterRepository registerRepository;
	@Value("${jwt.secret}")
	private String SECRET_KEY;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Long extractId(String token) {
		final Long id = extractAllClaims(token).get("id", Long.class);
		return id;
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(Base64.getEncoder().encode(SECRET_KEY.getBytes())).parseClaimsJws(token.replace("Bearer",""))
				.getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		Optional<Register> optionalUser = registerRepository.findByContact(userDetails.getPassword());
		Register user = optionalUser.get();

		claims.put("id", user.getId());
		claims.put("name", user.getName());
		claims.put("role", user.getRoles());
	 

		return createToken(claims, userDetails.getUsername());
	}




	private String createToken(Map<String, Object> claims, String subject) {

		// Two hours

		Date issuedTime = new Date(System.currentTimeMillis());

		Date expirationTime = new Date(System.currentTimeMillis() + 86400000);

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(issuedTime)
				.setExpiration(expirationTime)
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(SECRET_KEY.getBytes())).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		if (isTokenExpired(token))
			throw new ResourceNotFoundException("O token expirou!");

		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()));
	}

}
