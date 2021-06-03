package com.mea.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "beliver")

public class Beliver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message = "nome nao pode estar vazio")
	private String name;
	
	@Column(nullable = false)
	@NotBlank(message = "Conctacto nao pode estar vazio")
	private String contact;
	
	@Column(nullable = false)
	@NotBlank(message = "Conctacto familiar nao pode estar vazio")
	private String parentContact;
	
	@Column(name = "created_at")
	private LocalDateTime date;
	
	@Column(nullable = false)
	@NotBlank(message = "Endereco nao pode estar vazio")
	private String address;
	
	private int num_block;
	private int num_home;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getParentContact() {
		return parentContact;
	}
	public void setParentContact(String parentContact) {
		this.parentContact = parentContact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getNum_block() {
		return num_block;
	}
	public void setNum_block(int num_block) {
		this.num_block = num_block;
	}
	public int getNum_home() {
		return num_home;
	}
	public void setNum_home(int num_home) {
		this.num_home = num_home;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

		
	

	
	
}
