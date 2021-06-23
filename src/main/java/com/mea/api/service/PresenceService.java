package com.mea.api.service;


import java.sql.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mea.api.model.Presence;

@Service
public interface PresenceService {

	Presence registerBeliverToCult(Presence presence, Long idBeliver, String cult, Long idRegister);
	
	Map<String, Object> getPresences(int page);
	
	int quantityOfBeliversOnCult(String cult);
	
	Map<String, Object> getPresences(Date date, String cult, int page);
}