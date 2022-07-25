package com.gradle.cwt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class AccountingLineController {

	public boolean isValid(String value) {
		boolean isValid = false;
		isValid = value != null ? fieldContainsLetter(value) : isValid;
		return isValid;
	}
	
	public boolean fieldContainsLetter(String value) {
		boolean isValid = true;
		for(Character character : value.toCharArray()) {
			if(Character.isLetter(character)) {
				isValid = false;
			}
		}
		return isValid;
	}
	
	public Map<String, String> validate(BindingResult bindingResult){
		Map<String, String> map = new HashMap<>();
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return map;
	}
}
