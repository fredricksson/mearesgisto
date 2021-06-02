package com.mea.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mea.api.misc.ApiResponseObject;
import com.mea.api.model.Presence;
import com.mea.api.service.PresenceService;
import com.mea.api.service.RegisterService;
import com.mea.api.util.JwtUtil;

@RestController
@RequestMapping("/api/v1")
public class PresenceController {
	
	@Autowired
	PresenceService presenceService;
	
	@Autowired
	RegisterService registerService;
	
	@Autowired
	JwtUtil jwtUtil;
	@PostMapping("/presence/{idBeliver}/{cult}")
	public ResponseEntity<Map<String, Object>> registerBeliver(@Valid @RequestBody Presence presence, @PathVariable("idBeliver") Long idBeliver, @PathVariable("cult") String cult,HttpServletRequest req){
		Long  id = jwtUtil.extractId(req.getHeader("Authorization"));

		Map<String, Object> res = new ApiResponseObject().
	    response(Boolean.FALSE, "Registado com sucesso!", presenceService.registerBeliverToCult(presence, idBeliver, cult, id));
		return new ResponseEntity<>(res, HttpStatus.OK);
		
	}
	
	@GetMapping("presences")
	public ResponseEntity<Map<String, Object>> findAllPaginable(){
		return new ResponseEntity<>(presenceService.getPresences(0),HttpStatus.OK);
	}
	
	@GetMapping("presences/quantity/{name}")
	public ResponseEntity<Map<String, Object>> quantityOfBeliversOnCult(@PathVariable("name") String name){
		Map<String, Object> res = new ApiResponseObject().
	   response(Boolean.FALSE, "Sucesso!", presenceService.quantityOfBeliversOnCult(name));
	   return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
