package com.mea.api.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "beliver")
public class Beliver {

	@Id
	//@SequenceGenerator(name = "some",sequenceName = "seq_name_in_db",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY/*, generator = "some"*/)
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
	@OneToMany(mappedBy = "beliver", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Presence> presences;
	
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
	public List<Presence> getPresences() {
		return presences;
	}
	public void setPresences(List<Presence> presences) {
		this.presences = presences;
	}

		
	

	
	
}
