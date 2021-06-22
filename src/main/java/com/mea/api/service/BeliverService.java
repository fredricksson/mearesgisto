package com.mea.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.mea.api.dto.response.BeliverWithPresencesDTO;
import com.mea.api.model.Beliver;

@Service
public interface BeliverService {
	
	Beliver createBeliver (Beliver beliver);
	
	Beliver getBeliverById(Long id);
	
	List<Beliver> findAllBelivers();
	
	Beliver updateBeliver(Beliver beliver);
	
	Beliver findBeliverByContact(String contact);
	
	List<BeliverWithPresencesDTO>  findBeliverWithPresences();
	
	BeliverWithPresencesDTO findOneBeliver(Long id);
	
	Map<String,Object> findPresences(Long id, int page);
	
	Map<String,Object> findBeliverWithPresencesPageable(int page);

}
