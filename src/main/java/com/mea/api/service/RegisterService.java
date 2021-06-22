package com.mea.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mea.api.model.Register;



@Service
public interface RegisterService {

	
	Register findRegisterById(Long id);
	
	
	Register createRegister(Register resgister);
	
	Register createRegisterAdmin(Register resgister);
	
	List<Register> getRegisters();
	
	Register findByContact(String contact);
	
	Register verify(String name, String contact);
}
