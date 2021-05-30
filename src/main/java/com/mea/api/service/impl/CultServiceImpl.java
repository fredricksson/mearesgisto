package com.mea.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Cult> getCults() {
		// TODO Auto-generated method stub
		return null;
	}

}
