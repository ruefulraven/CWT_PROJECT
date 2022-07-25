package com.gradle.cwt.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradle.cwt.dto.AccountingLineXYZ;
import com.gradle.cwt.entity.AccountingLine;
import com.gradle.cwt.service.AccountingLineService;

@RestController()
@RequestMapping(path = "CWT")
public class AccountingLineAPI {

	@Autowired
	AccountingLineService service;
	
	@Autowired
	Environment environment;
	
	private AccountingLineController controller = new AccountingLineController();
	
	private Map<String, String> errorMap;
	
	private final String errorMessage = " must not be empty or contains special character or letter";
	
	@PostMapping(value = "/sendAccountingLineAPI", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getApi(@Valid @RequestBody AccountingLineXYZ request, BindingResult bindingResult) throws Exception, NumberFormatException{
		errorMap = controller.validate(bindingResult);
		request = checkNumberFields(request);
		
		ResponseEntity<?> response = errorMap.isEmpty() ? 
				new ResponseEntity<>(service.accountingLineBuilder(request), HttpStatus.OK) : requestWithError(request);
		
		return response;
	}
	
	public ResponseEntity<AccountingLine> requestWithError(AccountingLineXYZ request){
		request.setErrors(errorMap);
		return new ResponseEntity<>(service.accountingLineBuilder(request), HttpStatus.OK);
	}
		
	public AccountingLineXYZ checkNumberFields(AccountingLineXYZ request) {
		//default values for fields that have incorrect format
		String commissionAmt = controller.isValid(request.getCommissionAmount()) ?
				request.getCommissionAmount() : setDefaultValues("commissionAmt", "20");
		String commissionPercent = controller.isValid(request.getCommissionPercentage()) ?
				request.getCommissionPercentage() : setDefaultValues("commissionPercent", "20");
		String qstAmount = controller.isValid(request.getQstAmount()) ?
				request.getQstAmount() : setDefaultValues("qstAmount", "15");
		String gstAmount = controller.isValid(request.getGstAmount()) ?
				request.getGstAmount() : setDefaultValues("gstAmount", "15");
		String taxAmount = controller.isValid(request.getTaxAmount()) ?
				request.getTaxAmount() : setDefaultValues("taxAmount", "100");
		String baseFare = controller.isValid(request.getBaseFare()) ?
				request.getBaseFare() : setDefaultValues("baseFare", "50");
		request.setCommissionAmount(commissionAmt);
		request.setCommissionPercentage(commissionPercent);
		request.setQstAmount(qstAmount);
		request.setGstAmount(gstAmount);
		request.setTaxAmount(taxAmount);
		request.setBaseFare(baseFare);
		return request;
	}
	
	public String setDefaultValues(String fieldName, String fieldValue) {
		errorMap.put(fieldName, errorMessage);
		return fieldValue;
	}
}
