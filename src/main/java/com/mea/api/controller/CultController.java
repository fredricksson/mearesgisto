package com.mea.api.controller;

import java.util.Map;

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
import com.mea.api.model.Cult;
import com.mea.api.service.CultService;

@RestController
@RequestMapping("/api/v1")
public class CultController {

	@Autowired
	CultService cultService;
	
	@PostMapping("admin/cults")
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Cult cult ){
		Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, "Adiccionado com sucesso!", cultService.create(cult));
		return new ResponseEntity<>(res,  HttpStatus.OK);
	}
	@GetMapping("admin/cults/{page}")
	public ResponseEntity<Map<String, Object>> getCultsPaginaded(@PathVariable("page") int page){
		Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, " sucesso!", cultService.getCults(page));
		return new ResponseEntity<>(res,  HttpStatus.OK);
	}
	@GetMapping("/cults")
	public ResponseEntity<Map<String, Object>> all(){
		Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, " sucesso!", cultService.all());
		return new ResponseEntity<>(res,  HttpStatus.OK);
	}
	 @GetMapping("admin/cults/search/{value}/{page}")
	    public ResponseEntity<Map<String, Object>> searchBeliver(@PathVariable("value") String value,@PathVariable("page") int page){
	    	Map<String, Object> res = new ApiResponseObject().response(Boolean.FALSE, "Successo!", cultService.searcCultByName(value, page));
	    	return  new ResponseEntity<>(res, HttpStatus.OK);
	    }
}
