package com.mea.api.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity(name = "presence")
@Table(name = "presence")

public class Presence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private LocalDateTime date;
	
	private float temperature;
	
	
	
	@ManyToOne
	//@JsonBackReference
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


	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	




	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
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
