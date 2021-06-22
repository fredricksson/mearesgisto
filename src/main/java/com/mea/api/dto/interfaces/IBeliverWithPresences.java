package com.mea.api.dto.interfaces;

import java.util.List;

import com.mea.api.dto.response.PresenceDTO;

public class IBeliverWithPresences {
    
	public Long getId;
	
	public String getName;
	public String getContact;
	
	
	public int getNum_block;
	
	public int getNum_home;
	
	public List<PresenceDTO> getPresences;
}
