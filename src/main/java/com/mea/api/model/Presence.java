package com.mea.api.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;



@Entity
@Table(name = "presence")

public class Presence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	private LocalDateTime date;
	
	private float temperature;
	
	
	
	@OneToOne
	@JoinColumn(name = "beliver_id", nullable = false)
	private Beliver beliver;
	
	@OneToOne
	@JoinColumn(name = "register_id", nullable = false)
	private Register register;
	
	@OneToOne
	@JoinColumn(name = "cult_id", nullable = false)
	private Cult cult;
	
	

	public void setId(Long id) {
		this.id = id;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	

	public void setBeliver(Beliver beliver) {
		this.beliver = beliver;
	}
   

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public void setCult(Cult cult) {
		this.cult = cult;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public float getTemperature() {
		return temperature;
	}

	
	public Beliver getBeliver() {
		return beliver;
	}

	public Cult getCult() {
		return cult;
	}

	
	
}
