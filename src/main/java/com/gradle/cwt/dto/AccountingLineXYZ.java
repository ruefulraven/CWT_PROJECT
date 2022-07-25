package com.gradle.cwt.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "AccountingLine")
@XmlAccessorType(XmlAccessType.FIELD)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlType(propOrder = {
        "typeIndicator",
        "fareApplication",
        "formOfPaymentCode",
        "linkCode",
        "accountingVendorCode",
        "chargeCategoryCoded",
        "airlineDesignator",
        "documentNumber",
        "commissionPercentage",
        "commissionAmount",
        "baseFare",
        "basePercent",
        "taxPercentage",
        "taxAmount",
        "taxSurchargeCode2",
        "gstCode",
        "gstAmount",
        "gstPercent",
        "qstCode",
        "qstAmount",
        "qstPercent",
        "creditCardNumber",
        "creditCardCode",
        "passengerName",
        "numberOfConjunctedDocuments",
        "numberOfCoupons",
        "originalTicketNumber",
        "originalDateOfIssue",
        "originalPlaceOfIssue",
        "fullPartialExchangeIndicator",
        "originalInvoice",
        "tarriffBasis",
        "freeFormText",
        "currencyCode",
        "segmentType",
        "segmentNumber"
})
public class AccountingLineXYZ implements Serializable
{
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "TypeIndicator")
	@JacksonXmlProperty(localName = "TypeIndicator")
	@NotEmpty(message = "Type Indicator must not be empty")
    protected String typeIndicator;
	@XmlElement(name = "FareApplication")
	@NotBlank(message = "Fare Application must not be empty")
    @JacksonXmlProperty(localName = "FareApplication")
    protected String fareApplication;
	@XmlElement(name = "FormOfPaymentCode")
	@NotBlank(message = "Form of payment code must not be empty")
    @JacksonXmlProperty(localName = "FormOfPaymentCode")
    protected String formOfPaymentCode;
	@XmlElement(name = "LinkCode")
	@NotBlank(message = "Link Code must not be empty")
    @JacksonXmlProperty(localName = "LinkCode")
    protected String linkCode;
	@XmlElement(name = "AccountingVendorCode")
	@NotBlank(message = "Accounting Vendor Code must not be empty")
    @JacksonXmlProperty(localName = "AccountingVendorCode")
    protected String accountingVendorCode;
	@XmlElement(name = "ChargeCategoryCoded")
	@NotBlank(message = "Charge Category Code must not be empty")
    @JacksonXmlProperty(localName = "ChargeCategoryCoded")
    protected String chargeCategoryCoded;
	@XmlElement(name = "AirlineDesignator")
	@NotBlank(message = "Airline designator must not be empty")
    @JacksonXmlProperty(localName = "AirlineDesignator")
    protected String airlineDesignator;
	@XmlElement(name = "DocumentNumber")
	@NotBlank(message = "Document Number must not be empty")
    @JacksonXmlProperty(localName = "DocumentNumber")
    protected String documentNumber;
	@NotBlank(message = "Commision Percentage must not be empty")
    @JacksonXmlProperty(localName = "CommissionPercentage")
	@XmlElement(name = "CommissionPercentage")
    protected String commissionPercentage;
	@NotBlank(message = "Commission Amount must not be empty")
    @JacksonXmlProperty(localName = "CommissionAmount")
	@XmlElement(name = "CommissionAmount")
    protected String commissionAmount;
	@NotBlank(message = "Base Fare must not be empty")
    @JacksonXmlProperty(localName = "BaseFare")
	@XmlElement(name = "BaseFare")
    protected String baseFare;
    @JacksonXmlProperty(localName = "BasePercent")
    @XmlElement(name = "BasePercent")
    protected String basePercent;
    @XmlElement(name = "TaxPercentage")
	@NotBlank(message = "Tax Percentage must not be empty")
    @JacksonXmlProperty(localName = "TaxPercentage")
    protected String taxPercentage;
	@NotBlank(message = "Tax Amount must not be empty")
    @JacksonXmlProperty(localName = "TaxAmount")
	@XmlElement(name = "TaxAmount")
    protected String taxAmount;
	@XmlElement(name = "TaxSurchargeCode2")
    @JacksonXmlProperty(localName = "TaxSurchargeCode2")
    protected String taxSurchargeCode2;
	@XmlElement(name = "GSTCode")
    @NotBlank(message = "GST Code must not be empty")
    @JacksonXmlProperty(localName = "GSTCode")
    protected String gstCode;
    @NotBlank(message = "GST Amount must not be empty")
    @JacksonXmlProperty(localName = "GSTAmount")
    @XmlElement(name = "GSTAmount")
    protected String gstAmount;
    @XmlElement(name = "GSTPercent")
    @NotBlank(message = "GST Percent must not be empty")
    @JacksonXmlProperty(localName = "GSTPercent")
    protected String gstPercent;
    @XmlElement(name = "QSTCode")
    @NotBlank(message = "QST Code must not be empty")
    @JacksonXmlProperty(localName = "QSTCode")
    protected String qstCode;
    @NotBlank(message = "QST Amount must not be empty")
    @XmlElement(name = "QSTAmount")
    @JacksonXmlProperty(localName = "QSTAmount")
    protected String qstAmount;
    @XmlElement(name = "QSTPercent")
    @NotBlank(message = "QST Percent must not be empty")
    @JacksonXmlProperty(localName = "QSTPercent")
    protected String qstPercent;
    @XmlElement(name = "CreditCardNumber")
    @NotBlank(message = "Credit Card Number must not be empty")
    @JacksonXmlProperty(localName = "CreditCardNumber")
    protected String creditCardNumber;
    @XmlElement(name = "CreditCardCode")
    @NotBlank(message = "Credit Card Code must not be empty")
    @JacksonXmlProperty(localName = "CreditCardCode")
    protected String creditCardCode;
    @XmlElement(name = "PassengerName")
    @NotBlank(message = "Passenger Name must not be empty")
    @JacksonXmlProperty(localName = "PassengerName")
    protected String passengerName;
    @XmlElement(name = "NumberOfConjunctedDocuments")
    @NotBlank(message = "Number of conjuncted Documents must not be empty")
    @JacksonXmlProperty(localName = "NumberOfConjunctedDocuments")
    protected String numberOfConjunctedDocuments;
    @XmlElement(name = "NumberOfCoupons")
    @NotBlank(message = "Number of coupons must not be empty")
    @JacksonXmlProperty(localName = "NumberOfCoupons")
    protected String numberOfCoupons;
    @XmlElement(name = "OriginalTicketNumber")
    @NotBlank(message = "Original ticket number must not be empty")
    @JacksonXmlProperty(localName = "OriginalTicketNumber")
    protected String originalTicketNumber;
    @XmlElement(name = "OriginalDateOfIssue")
    @NotBlank(message = "Original date of issue must not be empty")
    @JacksonXmlProperty(localName = "OriginalDateOfIssue")
    protected String originalDateOfIssue;
    @XmlElement(name = "OriginalPlaceOfIssue")
    @NotBlank(message = "Original place of issue must not be empty")
    @JacksonXmlProperty(localName = "OriginalPlaceOfIssue")
    protected String originalPlaceOfIssue;
    @XmlElement(name = "FullPartialExchangeIndicator")
    @NotBlank(message = "Full partial exchange indicator must not be empty")
    @JacksonXmlProperty(localName = "FullPartialExchangeIndicator")
    protected String fullPartialExchangeIndicator;
    @XmlElement(name = "OriginalInvoice")
    @NotBlank(message = "Original invoice must not be empty")
    @JacksonXmlProperty(localName = "OriginalInvoice")
    protected String originalInvoice;
    @XmlElement(name = "TarriffBasis")
    @NotBlank(message = "Tarriff basis must not be empty")
    @JacksonXmlProperty(localName = "TarriffBasis")
    protected String tarriffBasis;
    @XmlElement(name = "FreeFormText")
    @NotBlank(message = "Free form text must not be empty")
    @JacksonXmlProperty(localName = "FreeFormText")
    protected String freeFormText;
    @XmlElement(name = "CurrencyCode")
    @NotBlank(message = "Currency code must not be empty")
    @JacksonXmlProperty(localName = "CurrencyCode")
    protected String currencyCode;
    @XmlElement(name = "SegmentType")
    @NotBlank(message = "SegmentType must not be empty")
    @JacksonXmlProperty(localName = "SegmentType")
    protected String segmentType;
    @XmlElement(name = "SegmentNumber")
    @NotBlank(message = "Segment number must not be empty")
    @JacksonXmlProperty(localName = "SegmentNumber")
    protected String segmentNumber;
    @NotBlank(message = "Id must not be empty")
    @XmlAttribute(name = "id")
    protected String id;
    @NotNull(message = "Index must not be empty")
    @XmlAttribute(name = "index")
    protected Integer index;
    @NotBlank(message = "Element id must not be empty")
    @XmlAttribute(name = "elementId")
    protected String elementId;
    
    protected Map<String, String> errors;
    
}
