package com.gradle.cwt.service;

import java.util.List;

import com.gradle.cwt.dto.AccountingLineXYZ;
import com.gradle.cwt.entity.AccountingLine;
import com.gradle.cwt.entity.MonetaryAmount;
import com.gradle.cwt.entity.MonetaryPercentage;


public interface AccountingLineService {
	
	public AccountingLine accountingLineBuidler();
	
	public AccountingLine accountingLineBuilder(AccountingLineXYZ accountingLineXYZ);
	
	public String countConjunctedDocuments(String documents);
	
	public List<String> refIDList(String number);
	
	public Long parseLong(String values);
	
	public String formatValue(String number);
	
	public MonetaryAmount baseFare(AccountingLineXYZ xmlFile);

	public MonetaryAmount taxAmount(AccountingLineXYZ xmlFile);

	public MonetaryAmount totalTaxAmount(AccountingLineXYZ xmlFile, MonetaryAmount totalTaxSurcharge);

	public MonetaryAmount totalTaxSurcharge(Long qstAmount, Long gstAmount);

	public MonetaryAmount gstAmount(AccountingLineXYZ xmlFile);

	public MonetaryAmount qstAmount(AccountingLineXYZ xmlFile);

	public MonetaryAmount commision(AccountingLineXYZ xmlFile);
	
	public MonetaryPercentage commissionDetails(AccountingLineXYZ xmlFile, MonetaryAmount commision);

}
