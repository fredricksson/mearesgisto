package com.mea.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mea.api.error.RegisterException;
import com.mea.api.error.ResourceNotFoundException;
import com.mea.api.model.Register;
import com.mea.api.model.Role;
import com.mea.api.repository.RegisterRepository;
import com.mea.api.service.RegisterService;
import com.mea.api.service.RoleService;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	RegisterRepository registerRepository;
	
	@Autowired
	RoleService roleService;
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
			resgister.setDate(LocalDateTime.now());
			Role role = roleService.findRoleByName("ROLE_GUEST");
			resgister.getRoles().add(role);
			return registerRepository.save(resgister);
			
		} catch (Exception e) {
			throw new RegisterException(e.getMessage());
		}
	}
	
	@Override
	public Register createRegisterAdmin(Register resgister) {
		try {
			resgister.setDate(LocalDateTime.now());
			return registerRepository.save(resgister);
			
		} catch (Exception e) {
			throw new RegisterException(e.getMessage());
		}
	}

	@Override
	public Register findByContact(String contact) {
		try {
			return registerRepository.findByContact(contact.trim()).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Registador nao encontrado");
		}
	}

	@Override
	public Register verify(String name, String contact) {
		try {
			return registerRepository.verify(name.trim(), contact.trim()).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Credencias invalidas");
		}
	}
	

}
