package com.mea.api.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "role")
@Table(name = "role")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	@NotBlank(message = "Error: Role name is mandatory!")
	@NonNull
	private String name;

	
	
	public Role(@NotBlank(message = "Error: Role name is mandatory!") @NonNull String name) {
		this.name = name;
	}
	

	public Role() {
	}


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
