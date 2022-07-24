package com.gradle.cwt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradle.cwt.dto.AccountingLineXYZ;
import com.gradle.cwt.service.AccountingLineService;

@RestController()
@RequestMapping(path = "CWT")
public class AccountingLineController {

	@Autowired
	AccountingLineService service;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value = "/sendAccountingLineAPI", consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> getApi(@Valid @RequestBody AccountingLineXYZ request, BindingResult bindingResult) throws Exception, NumberFormatException{
		Map<String, String> errorMap = validate(bindingResult);
		ResponseEntity<?> response = errorMap.isEmpty() ? 
				new ResponseEntity<>(service.accountingLineBuilder(request), HttpStatus.OK) : new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		
		return response;
	}
		
	@GetMapping("/retrieveAccountingLineAPI")
	public ResponseEntity<?> retrieveAccountingLineAPITemp(){
		return new ResponseEntity<>(service.accountingLineBuidler(), HttpStatus.OK);
	}
	
	public Map<String, String> validate(BindingResult bindingResult){
		Map<String, String> map = new HashMap<>();
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return map;
	}
}
