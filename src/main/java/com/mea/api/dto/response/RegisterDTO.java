package com.mea.api.dto.response;

import com.mea.api.model.Register;

public class RegisterDTO {
	
	Long id;
	
	String name;

	public RegisterDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public RegisterDTO() {
		super();
	}

	public static RegisterDTO RegisterToDTO(Register register) {
		return new RegisterDTO(register.getId(), register.getName());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
}
