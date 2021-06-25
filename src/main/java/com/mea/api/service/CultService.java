package com.mea.api.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mea.api.model.Cult;

@Service
public interface CultService {
	
	Cult findCultById(Long id);
	
	Cult create(Cult cult);
	
	Map<String, Object> getCults(int page);
	
	Map<String, Object> searcCultByName(String value,int page);

}
