package com.mea.api.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "register")
@Table(name = "register")
@JsonIgnoreProperties(value = { "contact", "roles" })
public class Register {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	
	@NotBlank(message = "O nome do registador nao pode estar vazio")
	private String name;
	
	@NotBlank(message = "O contacto do registador nao pode estar vazio")
	private String contact;

	@Column(name = "created_at")
	private LocalDateTime date;
	
	@ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY /* FetchType.EAGER*/)
	@JoinTable(name = "users_roles",  joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
	
	public Register() {
	}

	public Register(Long id, @NotBlank(message = "O nome do registador nao pode estar vazio") String name,
			@NotBlank(message = "O contacto do registador nao pode estar vazio") String contact, LocalDateTime date,
			Set<Role> roles) {
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.date = date;
		this.roles = roles;
	}

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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
