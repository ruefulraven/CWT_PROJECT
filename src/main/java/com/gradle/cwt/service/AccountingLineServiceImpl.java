package com.gradle.cwt.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.gradle.cwt.dto.AccountingLineXYZ;
import com.gradle.cwt.entity.AccountingLine;
import com.gradle.cwt.entity.AccountingLineStatus;
import com.gradle.cwt.entity.MonetaryAmount;
import com.gradle.cwt.entity.MonetaryPercentage;


@Service
public class AccountingLineServiceImpl implements AccountingLineService{
	
	@Override
	public AccountingLine accountingLineBuilder(AccountingLineXYZ accountingLineXYZ) {
		AccountingLine accountingLine = null;
		MonetaryAmount qstAmount = qstAmount(accountingLineXYZ);
		MonetaryAmount gstAmount = gstAmount(accountingLineXYZ);
		MonetaryAmount totalTaxSurcharge = totalTaxSurcharge(gstAmount.getValue(), qstAmount.getValue());
		MonetaryAmount baseFare = baseFare(accountingLineXYZ);
		MonetaryAmount taxAmount = taxAmount(accountingLineXYZ);
		MonetaryAmount totalTaxAmount = totalTaxAmount(accountingLineXYZ, totalTaxSurcharge);
		MonetaryAmount commision = commision(accountingLineXYZ);
		MonetaryPercentage commissionDetails = commissionDetails(accountingLineXYZ, commision);
		AccountingLine accountingLineTemp = AccountingLine.builder()
				.accountingLineID(accountingLineXYZ.getId())
				.accountingLineStatus(AccountingLineStatus.ACTIVE)
				.airlineCode(null)
				.accountingVendorCode(accountingLineXYZ.getAccountingVendorCode())
				.chargeCategoryCode(accountingLineXYZ.getChargeCategoryCoded())
				.formattedReceiptNumber(null)
				.invoiceNumber(accountingLineXYZ.getOriginalInvoice())
				.linkCode(accountingLineXYZ.getLinkCode())
				.numberOfConjunctedDocuments(countConjunctedDocuments(accountingLineXYZ.getNumberOfConjunctedDocuments()))
				.originalTicketNumber(accountingLineXYZ.getOriginalTicketNumber())
				.receiptNumber(accountingLineXYZ.getDocumentNumber())
				.segmentRefIDList(refIDList(accountingLineXYZ.getSegmentNumber()))
				.travelerName(accountingLineXYZ.getPassengerName())
				.travelerRefIDList(refIDList("1"))
				.typeIndicator(accountingLineXYZ.getTypeIndicator())
				.elementNumber(accountingLineXYZ.getIndex().toString())
				.fareApplication(accountingLineXYZ.getFareApplication())
				.baseFare(baseFare)
				.taxAmount(taxAmount)
				.totalTaxAmount(totalTaxAmount)
				.totalTaxSurcharge(totalTaxSurcharge)
				.gstAmount(gstAmount)
				.gstCode(accountingLineXYZ.getGstCode())
				.qstAmount(qstAmount)
				.qstCode(accountingLineXYZ.getQstCode())
				.commission(commissionDetails)
				.freeFormText(accountingLineXYZ.getFreeFormText())
				.build();
		
		accountingLine = accountingLineTemp;
		return accountingLine;
	}

	@Override
	public String countConjunctedDocuments(String documents) {
		char[] documentCharacters = documents.toCharArray();
		int count = 1;
		for(char letter: documentCharacters) {
			count = (letter == '-') ? count+1 : count;
		}
		String countOfDocuments = Integer.toString(count);
		return countOfDocuments;
	}

	@Override
	public List<String> refIDList(String segmentNumber) {
		List<String> idList = new ArrayList<>();
		idList.add(segmentNumber);
		return idList;
	}

	@Override
	public Long parseLong(String value) {
		CharSequence charSequence = ".";
		Long realValue = (value.contains(charSequence)) 
				? Math.round(Double.valueOf(value)) : Long.parseLong(Integer.valueOf(value).toString());
		return realValue;
	}

	@Override
	public String formatValue(String number) {
		String value = "$".concat(number);
		return value;
	}

	@Override
	public MonetaryAmount qstAmount(AccountingLineXYZ xmlFile) {
		MonetaryAmount monetary = MonetaryAmount.builder()
				.value(parseLong(xmlFile.getQstAmount()))
				.build();
		return monetary;
	}

	@Override
	public MonetaryAmount baseFare(AccountingLineXYZ xmlFile) {
		MonetaryAmount monetaryAmount = MonetaryAmount.builder()
				.value(parseLong(xmlFile.getBaseFare()))
				.currencyCode(xmlFile.getCurrencyCode())
				.formattedValue(formatValue(xmlFile.getBaseFare()))
				.build();
		return monetaryAmount;
	}

	@Override
	public MonetaryAmount taxAmount(AccountingLineXYZ xmlFile) {
		MonetaryAmount monetaryAmount = MonetaryAmount.builder()
				.value(parseLong(xmlFile.getTaxAmount()))
				.build();
		return monetaryAmount;
	}

	@Override
	public MonetaryAmount totalTaxAmount(AccountingLineXYZ xmlFile, MonetaryAmount totalTaxSurcharge) {
		MonetaryAmount monetaryAmount = MonetaryAmount.builder()
				.value(parseLong(xmlFile.getTaxAmount()) + totalTaxSurcharge.getValue())
				.build();
		return monetaryAmount;
	}

	@Override
	public MonetaryAmount totalTaxSurcharge(Long qstAmount, Long gstAmount) {
		MonetaryAmount monetaryAmount = MonetaryAmount.builder()
				.value(gstAmount + qstAmount)
				.build();
		return monetaryAmount;
	}

	@Override
	public MonetaryAmount gstAmount(AccountingLineXYZ xmlFile) {
		MonetaryAmount monetary = MonetaryAmount.builder()
				.value(parseLong(xmlFile.getGstAmount()))
				.build();
		return monetary;
	}
	
	@Override
	public MonetaryAmount commision(AccountingLineXYZ xmlFile) {
		MonetaryAmount monetary = MonetaryAmount.builder()
				.value(parseLong(xmlFile.getCommissionAmount()))
				.build();
		return monetary;
	}

	@Override
	public MonetaryPercentage commissionDetails(AccountingLineXYZ xmlFile, MonetaryAmount commision) {
		BigDecimal percent = BigDecimal.valueOf(parseLong(xmlFile.getCommissionPercentage()));
		MonetaryPercentage commissionDetails = MonetaryPercentage.builder()
				.amount(commision)
				.percentage(percent)
				.build();
		return commissionDetails;
	}
	
	//For JUnit purposes
	@Override
	public AccountingLine accountingLineBuidler() {
		AccountingLine accountingLine = null;
		try {
			File file = new File("src\\\\main\\\\resources\\\\AccountingLine.xml");
			JAXBContext context = JAXBContext.newInstance(AccountingLineXYZ.class);
			Unmarshaller unmarshaller = (Unmarshaller) context.createUnmarshaller();
			AccountingLineXYZ xmlFile = (AccountingLineXYZ) unmarshaller.unmarshal(file);
			xmlFile.setQstAmount(xmlFile.getQstAmount().replace("null", ""));
			xmlFile.setBaseFare(xmlFile.getBaseFare().replace("null", ""));
			xmlFile.setTaxAmount(xmlFile.getTaxAmount().replace("null", ""));
			xmlFile.setTaxAmount(xmlFile.getTaxAmount().replace("null", ""));
			xmlFile.setGstAmount(xmlFile.getGstAmount().replace("null", ""));
			xmlFile.setQstAmount(xmlFile.getQstAmount().replace("null", ""));
			xmlFile.setCommissionAmount(xmlFile.getCommissionAmount().replace("null", ""));
			xmlFile.setQstAmount(xmlFile.getQstAmount().replace("null", ""));
			xmlFile.setCommissionPercentage(xmlFile.getCommissionPercentage().replace("null", ""));
			MonetaryAmount qstAmount = qstAmount(xmlFile);
			MonetaryAmount gstAmount = gstAmount(xmlFile);
			MonetaryAmount totalTaxSurcharge = totalTaxSurcharge(qstAmount.getValue(), gstAmount.getValue());
			MonetaryAmount baseFare = baseFare(xmlFile);
			MonetaryAmount taxAmount = taxAmount(xmlFile);
			MonetaryAmount totalTaxAmount = totalTaxAmount(xmlFile, totalTaxSurcharge);
			MonetaryAmount commision = commision(xmlFile);
			MonetaryPercentage commissionDetails = commissionDetails(xmlFile, commision);
			AccountingLine accountingLineTemp = AccountingLine.builder()
					.accountingLineID(xmlFile.getId())
					.accountingLineStatus(AccountingLineStatus.ACTIVE)
					.airlineCode(null)
					.accountingVendorCode(xmlFile.getAccountingVendorCode())
					.chargeCategoryCode(xmlFile.getChargeCategoryCoded())
					.formattedReceiptNumber(null)
					.invoiceNumber(xmlFile.getOriginalInvoice())
					.linkCode(xmlFile.getLinkCode())
					.numberOfConjunctedDocuments(countConjunctedDocuments(xmlFile.getNumberOfConjunctedDocuments()))
					.originalTicketNumber(xmlFile.getOriginalTicketNumber())
					.receiptNumber(xmlFile.getDocumentNumber())
					.segmentRefIDList(refIDList(xmlFile.getSegmentNumber()))
					.travelerName(xmlFile.getPassengerName())
					.travelerRefIDList(refIDList("1"))
					.typeIndicator(xmlFile.getTypeIndicator())
					.elementNumber(xmlFile.getIndex().toString())
					.fareApplication(xmlFile.getFareApplication())
					.baseFare(baseFare)
					.taxAmount(taxAmount)
					.totalTaxAmount(totalTaxAmount)
					.totalTaxSurcharge(totalTaxSurcharge)
					.gstAmount(gstAmount)
					.gstCode(xmlFile.getGstCode())
					.qstAmount(qstAmount)
					.qstCode(xmlFile.getQstCode())
					.commission(commissionDetails)
					.freeFormText(xmlFile.getFreeFormText())
					.build();
			
			accountingLine = accountingLineTemp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountingLine;
	}
}
