package com.mea.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mea.api.model.Beliver;

@Service
public interface BeliverService {
	
	Beliver createBeliver (Beliver beliver);
	
	Beliver getBeliverById(Long id);
	
	List<Beliver> findAllBelivers();
	
	Beliver updateBeliver(Beliver beliver);
	
	Beliver findBeliverByContact(String contact);

}
