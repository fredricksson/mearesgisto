package com.mea.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mea.api.model.Cult;

@Service
public interface CultService {
	
	Cult findCultById(Long id);
	
	Cult create(Cult cult);
	
	List<Cult> getCults();

}
