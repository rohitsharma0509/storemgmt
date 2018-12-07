package com.app.myproject.dto.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductType", propOrder = { "category", "name", "code",
		"brandName", "quantity", "alertQuantity", "purchasePrice", "sellPrice" })
public class ProductType {
	@XmlElement(name = "category", required = true)
	protected String category;

	@XmlElement(name = "name", required = true)
	protected String name;

	@XmlElement(name = "code", required = true)
	protected String code;

	@XmlElement(name = "brandName", required = true)
	protected String brandName;

	@XmlElement(name = "quantity", required = true)
	protected String quantity;

	@XmlElement(name = "alertQuantity", required = true)
	protected String alertQuantity;

	@XmlElement(name = "purchasePrice", required = true)
	protected String purchasePrice;

	@XmlElement(name = "sellPrice", required = true)
	protected String sellPrice;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAlertQuantity() {
		return alertQuantity;
	}

	public void setAlertQuantity(String alertQuantity) {
		this.alertQuantity = alertQuantity;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}
}