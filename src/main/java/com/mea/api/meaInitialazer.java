package com.mea.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mea.api.model.Register;
import com.mea.api.model.Role;
import com.mea.api.service.RegisterService;
import com.mea.api.service.RoleService;


@Configuration
public class meaInitialazer {
	

	
	@Autowired
	RoleService roleService;
	
	@Autowired
	RegisterService registerService;

	@Bean
	CommandLineRunner commandLineRunner() {

		return (args) -> {

			if (roleService.findAllRoles().isEmpty()) {
				// Creates new Roles
				List<Role> roles = Arrays.asList( new Role("ROLE_ADMIN"), new Role("ROLE_GUEST"));

				// Initilize all roles
				roleService.initilizeRoles(roles);
				Role roleAdmin = roleService.findRoleByName("ROLE_ADMIN");

				// Creates an admin;
				Register admin = new Register();
				admin.setName("Admin@2020");
				admin.setContact("841234");
				admin.getRoles().add(roleAdmin);
				
				
				registerService.createRegisterAdmin(admin);
				}
			
		};
	}
}
