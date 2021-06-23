package com.mea.api.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mea.api.error.CultException;
import com.mea.api.error.ResourceNotFoundException;
import com.mea.api.model.Cult;
import com.mea.api.repository.CultRepository;
import com.mea.api.service.CultService;

@Service
public class CultServiceImpl implements CultService {
	@Autowired
	CultRepository cultRepository;
	@Override
	public Cult findCultById(Long id) {
		try {
			return cultRepository.findById(id).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Culto nao encontrado");
		}
	}

	@Override
	public Cult create(Cult cult) {
		try {
			return cultRepository.save(cult);
		} catch (Exception e) {
			throw new CultException("Nao foi possiver salvar o culto");
		}
	}

	@Override
	public Map<String, Object> getCults(int page) {
		// TODO Auto-generated method stub
		Map<String, Object>  data = new LinkedHashMap<>();
		Page<Cult> result = cultRepository.findAll(PageRequest.of(page, 30));
		
		data.put("page", result.getNumber());
		data.put("TotalRecords", result.getTotalElements());
		data.put("numPages", result.getTotalPages());
		data.put("cults", result.getContent());
		return data;
	}


}
