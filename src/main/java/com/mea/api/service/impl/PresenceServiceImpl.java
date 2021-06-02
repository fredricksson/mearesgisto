package com.mea.api.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mea.api.error.PresenceException;
import com.mea.api.error.ResourceNotFoundException;
import com.mea.api.model.Beliver;
import com.mea.api.model.Cult;
import com.mea.api.model.Presence;
import com.mea.api.model.Register;
import com.mea.api.repository.CultRepository;
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
	CultRepository cultRepository;

	
	@Autowired
	BeliverService beliverService;
	
	@Autowired
	RegisterService registerService;
	
	@Autowired
	CultService cultService;


	@Override
	@Transactional
	public Presence registerBeliverToCult(Presence presence, Long idBeliver, String name, Long idRegister) {
		try {
			java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
			Long idCult = cultRepository.getId(name);
			Presence pre = presenceRepository.verifyBeliverIsRegisted(sqlDate,idCult , idBeliver);
			if (pre == null) {
			Beliver beliver = beliverService.getBeliverById(idBeliver);
			Register register = registerService.findRegisterById(idRegister);
			Cult cult = cultService.findCultById(idCult);
			presence.setCult(cult);
			presence.setRegister(register);
			presence.setDate(LocalDateTime.now());
			presence.setBeliver(beliver);
			return	presenceRepository.save(presence);
			} else {
				throw new PresenceException("Crente ja foi registado para este culto!");
			}
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


	@Override
	public int quantityOfBeliversOnCult(String cult) {
		// TODO Auto-generated method stub
		try {
			java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
			Long idCult = cultRepository.getId(cult);
			return presenceRepository.quantityOfBeliversOnCult(idCult, sqlDate);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
		
	}

	
	

}
