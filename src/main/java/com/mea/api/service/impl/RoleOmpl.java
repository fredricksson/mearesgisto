package com.mea.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mea.api.model.Role;
import com.mea.api.repository.RoleRepository;
import com.mea.api.service.RoleService;
@Service
public class RoleOmpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public void initilizeRoles(List<Role> roles) {
		// TODO Auto-generated method stub
		roleRepository.saveAll(roles);
		
	}

	@Override
	public Role findRoleByName(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findAll().stream()
				.filter(role -> role.getName().toUpperCase().equals(name))
				.findFirst().get();
	}

	@Override
	public List<Role> findAllRoles() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

}
