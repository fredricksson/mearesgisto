package com.mea.api.dto.response;

import com.mea.api.model.Beliver;

public class BeliverResponseDTO {
	
	private Long id;
	
	private String name;
	
	private String contact;
	
	private String parent_contact;
	
	private int num_block;
	
	private int num_home;
	
	
	
	public BeliverResponseDTO(Long id, String name, String contact, String parent_contact, int num_block,
			int num_home) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.parent_contact = parent_contact;
		this.num_block = num_block;
		this.num_home = num_home;
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

	public String getParent_contact() {
		return parent_contact;
	}

	public int getNum_block() {
		return num_block;
	}

	public int getNum_home() {
		return num_home;
	}
	
	
}
