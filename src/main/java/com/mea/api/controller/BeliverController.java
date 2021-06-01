package com.mea.api.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mea.api.misc.ApiResponseObject;
import com.mea.api.model.Beliver;
import com.mea.api.service.BeliverService;

@RestController
@RequestMapping("/api/v1")
public class BeliverController {
	
	@Autowired
	BeliverService beliverService;
	
	@GetMapping("/belivers")
	public ResponseEntity<Map<String, Object>> all(){
		Map<String, Object> response = new ApiResponseObject().response(Boolean.FALSE, "Success!", beliverService.findAllBelivers());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("belivers/findByContact/{contact}")
	public ResponseEntity<Map<String,Object>> findByContact(@PathVariable("contact") String contact){
		Map<String, Object> res =  new ApiResponseObject().response(Boolean.FALSE, "Successo!", beliverService.findBeliverByContact(contact));
		return  new ResponseEntity<>(res, HttpStatus.OK);
	}
	@PostMapping("/belivers")
	public ResponseEntity<Map<String, Object>> newBeliver(@Valid @RequestBody Beliver beliver) {
		Map<String, Object> response = new ApiResponseObject().response(Boolean.FALSE, "Success!",beliverService.createBeliver(beliver));
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	} 
    @GetMapping("/belivers/{id}")
	public ResponseEntity<Map<String, Object>> getBeliverById(@PathVariable("id") Long id){
    	Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, "Successo!", beliverService.getBeliverById(id));
    	return  new ResponseEntity<>(res, HttpStatus.OK);
	}
    
    @PutMapping("belivers/{id}")
    public ResponseEntity<Map<String, Object>> updateBeliver(@Valid @RequestBody Beliver beliver, @PathVariable("id") Long id){
    	beliver.setId(id);
    	Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, "Successo!", beliverService.updateBeliver(beliver));
    	return  new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    
}
