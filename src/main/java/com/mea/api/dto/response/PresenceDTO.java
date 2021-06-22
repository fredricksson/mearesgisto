package com.mea.api.dto.response;

import java.time.LocalDateTime;

import com.mea.api.model.Presence;

public class PresenceDTO {
    private Long id;
	
	
	private LocalDateTime date;
	
	private float temperature;
	
	private RegisterDTO register;

	
	
	public PresenceDTO(Presence presence) {
		super();
		this.id = presence.getId();
		this.date = presence.getDate();
		this.temperature = presence.getTemperature();
		this.register = RegisterDTO.RegisterToDTO(presence.getRegister());
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

	public PresenceDTO() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public RegisterDTO getRegister() {
		return register;
	}
	
	
	
	
}
