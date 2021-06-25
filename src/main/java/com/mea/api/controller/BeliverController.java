package com.mea.api.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mea.api.dto.request.BeliverDTO;
import com.mea.api.dto.response.BeliverResponseDTO;
import com.mea.api.misc.ApiResponseObject;
import com.mea.api.model.Beliver;
import com.mea.api.service.BeliverService;

@RestController
@RequestMapping("/api/v1")
public class BeliverController {
	
	@Autowired
	BeliverService beliverService;
	
	
	@GetMapping("admin/belivers/{page}")
	public ResponseEntity<Map<String, Object>> all(@PathVariable int page){
		Map<String, Object> response = new ApiResponseObject().response(Boolean.FALSE, "Success!", beliverService.findAllBelivers(page));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("belivers/findByContact/{contact}")
	public ResponseEntity<Map<String,Object>> findByContact(@PathVariable("contact") String contact){
		Map<String, Object> res =  new ApiResponseObject().response(Boolean.FALSE, "Successo!", beliverService.findBeliverByContact(contact));
		return  new ResponseEntity<>(res, HttpStatus.OK);
	}
	@PostMapping("/belivers")
	public ResponseEntity<Map<String, Object>> newBeliver(@Valid @RequestBody BeliverDTO dto) {
		Beliver beliver = beliverService.createBeliver(dto.converter());
		
		Map<String, Object> response = new ApiResponseObject().response(Boolean.FALSE, "Success!", BeliverResponseDTO.BeiverToDTO(beliver));
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	} 
    @GetMapping("/belivers/{id}")
	public ResponseEntity<Map<String, Object>> getBeliverById(@PathVariable("id") Long id){
    	Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, "Successo!", beliverService.getBeliverById(id));
    	return  new ResponseEntity<>(res, HttpStatus.OK);
	}
    
    //return beliver with his presences
    @GetMapping("admin/belivers/presences/{id}")
   	public ResponseEntity<Map<String, Object>> getBeliversWithPresence(@PathVariable("id") Long id){
       	Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, "Successo!", beliverService.findBeliverWithPresences());
       	return  new ResponseEntity<>(res, HttpStatus.OK);
   	}
    
    @GetMapping("admin/belivers/presences/{id}/{page}")
   	public ResponseEntity<Map<String, Object>> getPresencesBysers(@PathVariable("id") Long id, @PathVariable("page") int page){
       	Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, "Successo!", beliverService.findPresences(id, page));
       	return  new ResponseEntity<>(res, HttpStatus.OK);
   	}
    
    @PutMapping("admin/belivers/{id}")
    public ResponseEntity<Map<String, Object>> updateBeliver(@Valid @RequestBody BeliverDTO beliver, @PathVariable("id") Long id){
    	beliver.setId(id);
    	Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, "Successo!", beliverService.updateBeliver(beliver));
    	return  new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    @GetMapping("admin/belivers/search/{value}/{page}")
    public ResponseEntity<Map<String, Object>> searchBeliver(@PathVariable("value") String value,@PathVariable("page") int page){
    	Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, "Successo!", beliverService.searcRegisterByName(value, page));
    	return  new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    
}
