package com.mea.api.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mea.api.error.ResourceNotFoundException;
import com.mea.api.model.Register;
import com.mea.api.repository.RegisterRepository;
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	RegisterRepository registerRepository;
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Register> register = registerRepository.findRegisterByName(name);
		register.orElseThrow(() -> new ResourceNotFoundException("Registador inválido: " + name));

		return register.map(MyUserDetails::new).get();
	}

}
