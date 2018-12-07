package com.app.myproject.dto.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductsType", propOrder = { "productTypes" })
public class ProductsType {
	@XmlElement(name = "product", required = true)
	List<ProductType> productTypes;

	public List<ProductType> getProductTypes() {
		if (productTypes == null) {
			productTypes = new ArrayList<ProductType>();
		}
		return this.productTypes;
	}
}