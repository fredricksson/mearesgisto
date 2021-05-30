package com.mea.api.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mea.api.error.PresenceException;
import com.mea.api.model.Beliver;
import com.mea.api.model.Cult;
import com.mea.api.model.Presence;
import com.mea.api.model.Register;
import com.mea.api.repository.PresenceRepository;
import com.mea.api.service.BeliverService;
import com.mea.api.service.CultService;
import com.mea.api.service.PresenceService;
import com.mea.api.service.RegisterService;

@Service
public class PresenceServiceImpl implements PresenceService{
	
	@Autowired
	PresenceRepository presenceRepository;

	
	@Autowired
	BeliverService beliverService;
	
	@Autowired
	RegisterService registerService;
	
	@Autowired
	CultService cultService;


	@Override
	public Presence registerBeliverToCult(Presence presence, Long idBeliver, Long idCult, Long idRegister) {
		try {
			Beliver beliver = beliverService.getBeliverById(idBeliver);
			Register register = registerService.findRegisterById(idRegister);
			Cult cult = cultService.findCultById(idCult);
			presence.setCult(cult);
			presence.setRegister(register);
			presence.setDate(LocalDateTime.now());
			presence.setBeliver(beliver);
			return	presenceRepository.save(presence);
		} catch (Exception e) {
				throw new PresenceException(e.getMessage());
		}
	}


	@Override
	public Map<String, Object> getPresences(int page) {
		Map<String, Object>  data = new LinkedHashMap<>();
		Page<Presence> result = presenceRepository.findAll(PageRequest.of(page, 100));
		
		data.put("page", result.getNumber());
		data.put("TotalRecords", result.getTotalElements());
		data.put("numPages", result.getTotalPages());
		data.put("presences", result.getContent());
		return data;
	}

	
	

}
