package com.gradle.cwt;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.gradle.cwt.entity.AccountingLine;
import com.gradle.cwt.service.AccountingLineService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class CwtTechnicalExamApplicationTests {

	Logger logger = Logger.getLogger(CwtTechnicalExamApplication.class.getName());
	
	@Autowired
	AccountingLineService service;
	
	private AccountingLine accountingLine;
	private Long commisionValue;
	private BigDecimal commisionPercentage;
	private Long totalTaxAmount;
	private Long taxAmount;
	private Long baseFare;
	
	@Test
	void contextLoads() {
	}
	
	@BeforeAll
	void initializeAccountingLine() {
		accountingLine = service.accountingLineBuidler();
		commisionValue = accountingLine.getCommission().getAmount().getValue();
		commisionPercentage = accountingLine.getCommission().getPercentage();
		totalTaxAmount = accountingLine.getTotalTaxAmount().getValue();
		taxAmount = accountingLine.getTaxAmount().getValue();
		baseFare = accountingLine.getBaseFare().getValue();
		logger.log(Level.INFO, "AccountingLine has been initialized");
	}
	
	@Test
	void checkIfAccountingLineExists() {
		Assert.notNull(accountingLine, "Object must not be null");
	}
	
	@Test
	void checkAccountingLineLogics() {
		Assert.isTrue(commisionValue == 2, "Commision value is not same");
		logger.log(Level.INFO, "Commision Amount: " + commisionValue);
		Assert.isTrue(commisionPercentage.equals(BigDecimal.valueOf(0.04)), "Commision percentage is not same");
		logger.log(Level.INFO, "Commision Percentage: " + commisionPercentage);
		Assert.isTrue(totalTaxAmount == 221, "Total Tax Amount is not same");
		logger.log(Level.INFO, "Total Tax Amt : " + totalTaxAmount);
		Assert.isTrue(taxAmount == 201, "Tax Amount is not same");
		logger.log(Level.INFO, "Tax Amt : " + taxAmount);
		Assert.isTrue(baseFare == 1252, "Tax Amount is not same");
		logger.log(Level.INFO, "Base Fare : " + baseFare);
	}

}
