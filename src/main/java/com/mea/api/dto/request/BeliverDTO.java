package com.mea.api.dto.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.mea.api.model.Beliver;

public class BeliverDTO {
	
	@Column(nullable = false)
	@NotBlank(message = "name nao pode estar vazio")
	private String name;
	
	@Column(nullable = false)
	@NotBlank(message = "contact nao pode estar vazio")
	private String contact;
	
	@Column(nullable = false)
	@NotBlank(message = "parent_contact nao pode estar vazio")
	private String parent_contact;
	
	@Column(nullable = false)
	@NotBlank(message = "nome nao pode estar vazio")
	private String address;
	
	private int num_block;
	
	private int num_home;
	
	
	public Beliver converter () {
		return new Beliver(name, contact, parent_contact, address, num_block, num_home);
	}


	public String getName() {
		return name;
	}


	public String getContact() {
		return contact;
	}


	public String getParent_contact() {
		return parent_contact;
	}


	public String getAddress() {
		return address;
	}


	public int getNum_block() {
		return num_block;
	}


	public int getNum_home() {
		return num_home;
	}
	
	
}
