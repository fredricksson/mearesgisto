package com.mea.api.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mea.api.misc.ApiResponseObject;
import com.mea.api.model.Register;
import com.mea.api.service.RegisterService;

@RestController
@RequestMapping("/api/v1")
public class RegisterController {
	
	@Autowired
	RegisterService registerService;
	
	@GetMapping("/admin/registers")
	public ResponseEntity<Map<String, Object>> all(){
		return new ResponseEntity<>(new ApiResponseObject().response(Boolean.FALSE, "Sucesso!",registerService.getRegisters()), HttpStatus.OK);
	}
	
	@PostMapping("/admin/registers")
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Register register) {
		Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, "Registador adicionado com sucesso!", registerService.createRegister(register));
		return new ResponseEntity<>(res, HttpStatus.OK);
				
	}

}
