package com.gradle.cwt;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.gradle.cwt.entity.AccountingLine;
import com.gradle.cwt.service.AccountingLineService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
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
	@DisplayName("check If AccountingLine Exists")
	@Order(value = 1)
	void checkIfAccountingLineExists() {
		Assert.notNull(accountingLine, "Object must not be null");
	}
	
	@Test
	@DisplayName("check AccountingLine Commission Value")
	@Order(value = 5)
	void checkAccountingLineCommissionValue() {
		Assert.isTrue(commisionValue == 2, "Commision value is not same");
		logger.log(Level.INFO, "Commision Amount: " + commisionValue);
	}
	
	@Test
	@DisplayName("check AccountingLine Commission Percentage")
	@Order(value = 6)
	void checkAccountingLineCommissionPercentage() {
		Assert.isTrue(commisionPercentage.equals(BigDecimal.valueOf(2)), "Commision percentage is not same");
		logger.log(Level.INFO, "Commision Percentage: " + commisionPercentage);
	}
	
	@Test
	@DisplayName("check AccountingLine Base Fare")
	@Order(value = 2)
	void checkAccountingLineBaseFare() {
		Assert.isTrue(baseFare == 1252, "Tax Amount is not same");
		logger.log(Level.INFO, "Base Fare : " + baseFare);
	}
	
	@Test
	@DisplayName("check AccountingLine TaxAmt")
	@Order(value = 3)
	void checkAccountingLineTaxAmt() {
		Assert.isTrue(taxAmount == 201, "Tax Amount is not same");
		logger.log(Level.INFO, "Tax Amt : " + taxAmount);
	}
	
	@Test
	@DisplayName("check AccountingLine Total Tax Amt")
	@Order(value = 4)
	void checkAccountingLineTotalTaxAmt() {
		Assert.isTrue(totalTaxAmount == 221, "Total Tax Amount is not same");
		logger.log(Level.INFO, "Total Tax Amt : " + totalTaxAmount);
	}

}
