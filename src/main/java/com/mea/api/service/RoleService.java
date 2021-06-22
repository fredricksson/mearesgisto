package com.mea.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mea.api.model.Role;

@Service
public interface RoleService {
	
	void initilizeRoles(List<Role> roles);
	
	Role findRoleByName(String name);
	
	List<Role> findAllRoles();

}