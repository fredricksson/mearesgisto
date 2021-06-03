package com.mea.api.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mea.api.error.BeliverExists;
import com.mea.api.error.ResourceNotFoundException;
import com.mea.api.misc.ApiResponseObject;
import com.mea.api.model.Beliver;
import com.mea.api.repository.BeliverRepository;
import com.mea.api.service.BeliverService;

import javassist.NotFoundException;

@Service
public class BeliverServiceImpl implements BeliverService {
	@Autowired
	BeliverRepository beliverRepository;
	@Override
	public Beliver createBeliver(Beliver beliver) {
		Beliver beliver1 = beliverRepository.findBeliverByContact(beliver.getContact());
		if (beliver1 == null) {
			beliver.setDate(LocalDateTime.now());
			return  beliverRepository.save(beliver);
		}
		
		throw new BeliverExists("Crente Existente!#"+beliver1.getName()+"#"+beliver1.getId());
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
			Beliver beliver = beliverRepository.findBeliverByContact(contact);
			if (beliver == null) {
				throw new ResourceNotFoundException("Nao existe nenhum crente com esse contacto");
			}
			return beliver;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Nao existe nenhum crente com esse contacto");
		}
		
		
	}
	

}
