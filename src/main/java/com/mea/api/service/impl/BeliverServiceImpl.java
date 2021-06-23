package com.mea.api.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mea.api.dto.interfaces.IBeliverWithPresences;
import com.mea.api.dto.interfaces.IPresenceDTO;
import com.mea.api.dto.request.BeliverDTO;
import com.mea.api.dto.response.BeliverWithPresencesDTO;
import com.mea.api.dto.response.PresenceDTO;
import com.mea.api.error.BeliverExists;
import com.mea.api.error.ResourceNotFoundException;
import com.mea.api.model.Beliver;
import com.mea.api.model.Presence;
import com.mea.api.repository.BeliverRepository;
import com.mea.api.repository.PresenceRepository;
import com.mea.api.service.BeliverService;

@Service
public class BeliverServiceImpl implements BeliverService {
	@Autowired
	BeliverRepository beliverRepository;
	
	@Autowired
	PresenceRepository presenceRepository;
	
	@Override
	public Beliver createBeliver(Beliver beliver) {
		Beliver beliver1 = null;
		try {
			beliver1 = beliverRepository.findBeliverByContact(beliver.getContact().trim());
			if (beliver1 == null) {
				beliver.setDate(LocalDateTime.now());
				return  beliverRepository.save(beliver);
			} else {
				throw new BeliverExists("Crente Existente!#"+beliver1.getName()+"#"+beliver1.getId());
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new BeliverExists(e.getMessage());
		}
		
		
		
		
	}
	@Override
	public Beliver getBeliverById(Long id) {
		try {
    		Beliver beliver = beliverRepository.findById(id).get();
    		return beliver;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Crente nao encontrado");
		}
	}
	@Override
	public List<Beliver> findAllBelivers() {
		return beliverRepository.findAll();
	}
	@Override
	public Beliver updateBeliver(Beliver beliver) {
		try {
		    beliverRepository.findById(beliver.getId()).get();
    		return beliverRepository.save(beliver);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Nao foi possivel actualizar o crente, id inexistente.");
		}
	}
	@Override
	public Beliver findBeliverByContact(String contact) {
		try {
			Beliver beliver = beliverRepository.findBeliverByContact(contact.trim());
			if (beliver == null) {
				throw new ResourceNotFoundException("Nao existe nenhum crente com esse contacto");
			}
			return beliver;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Nao existe nenhum crente com esse contacto");
		}
		
		
	}
	
	
	@Override
	public List<BeliverWithPresencesDTO>  findBeliverWithPresences() {
	
		return beliverRepository.findBeliverWithPresences();
		
	}
	@Override
	public BeliverWithPresencesDTO findOneBeliver(Long id) {
		// TODO Auto-generated method stub
		return beliverRepository.findOneBeliverWithPresences(id);
	}
	@Override
	public Map<String, Object> findPresences(Long id, int page) {
		
		Map<String, Object>  data = new LinkedHashMap<>();
		Page<IPresenceDTO> result = beliverRepository.findPresences(PageRequest.of(page, 30), id);
		
		data.put("page", result.getNumber());
		data.put("TotalRecords", result.getTotalElements());
		data.put("numPages", result.getTotalPages());
		data.put("presences", result.getContent());
		return data;
	}
	

}
