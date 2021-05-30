package com.mea.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mea.api.error.RegisterException;
import com.mea.api.error.ResourceNotFoundException;
import com.mea.api.model.Beliver;
import com.mea.api.model.Register;
import com.mea.api.repository.RegisterRepository;
import com.mea.api.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	RegisterRepository registerRepository;
	@Override
	public Register findRegisterById(Long id) {
		try {
    		Register register = registerRepository.findById(id).get();
    		return register;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Registador nao encontrado");
		}
		
	}

	@Override
	public List<Register> getRegisters() {
		// TODO Auto-generated method stub
		return registerRepository.findAll();
	}

	@Override
	public Register createRegister(Register resgister) {
		try {
			return registerRepository.save(resgister);
			
		} catch (Exception e) {
			throw new RegisterException(e.getMessage());
		}
	}

	@Override
	public Register findByContact(String contact) {
		try {
			return registerRepository.findByContact(contact).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Registador nao encontrado");
		}
	}

	@Override
	public Register verify(String name, String contact) {
		try {
			return registerRepository.verify(name, contact).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Credencias invalidas");
		}
	}
	

}
