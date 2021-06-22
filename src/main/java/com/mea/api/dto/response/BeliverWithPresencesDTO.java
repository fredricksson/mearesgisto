package com.mea.api.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.mea.api.dto.request.BeliverDTO;
import com.mea.api.model.Beliver;
import com.mea.api.model.Presence;

public class BeliverWithPresencesDTO {
	
private Long id;
	
	private String name;
	
	private String contact;
	
	
	private int num_block;
	
	private int num_home;
	
	private List<PresenceDTO> presences;
	
	
	
	public BeliverWithPresencesDTO(Beliver beliver) {
		super();
		this.id = beliver.getId();
		this.name = beliver.getName();
		this.contact = beliver.getContact();
		this.num_block = beliver.getNum_block();
		this.num_home = beliver.getNum_home();
		this.presences = beliver.getPresences().stream().map(PresenceDTO::new).collect(Collectors.toList());
		//this.books = category.getBooks().parallelStream().map(BookDTO::new).collect(Collectors.toList());

			
	}


	public BeliverWithPresencesDTO() {
		super();
	}


	public static BeliverResponseDTO BeiverToDTO(Beliver beliver) {
		return new BeliverResponseDTO(beliver.getId(), beliver.getName(), beliver.getContact(), beliver.getParentContact(),
				 beliver.getNum_block(), beliver.getNum_home());
	}
	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getContact() {
		return contact;
	}

	public int getNum_block() {
		return num_block;
	}

	public int getNum_home() {
		return num_home;
	}


	public List<PresenceDTO> getPresences() {
		return presences;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public void setNum_block(int num_block) {
		this.num_block = num_block;
	}


	public void setNum_home(int num_home) {
		this.num_home = num_home;
	}


	public void setPresences(List<PresenceDTO> presences) {
		this.presences = presences;
	}
	

}
