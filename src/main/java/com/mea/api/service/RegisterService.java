package com.mea.api.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mea.api.model.Register;



@Service
public interface RegisterService {

	
	Register findRegisterById(Long id);
	
	
	Register createRegister(Register resgister);
	
	Register updateRegister(Register resgister);
	
	Register createRegisterAdmin(Register resgister);
	
	Map<String, Object> getRegisters(int page);
	
	Register findByContact(String contact);
	
	Register verify(String name, String contact);
	
	Map<String,Object> searcRegisterByName(String value, int page);
}
