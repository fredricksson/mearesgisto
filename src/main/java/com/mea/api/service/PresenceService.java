package com.mea.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mea.api.model.Presence;

@Service
public interface PresenceService {

	Presence registerBeliverToCult(Presence presence, Long idBeliver, String cult, Long idRegister);
	
	Map<String, Object> getPresences(int page);
}