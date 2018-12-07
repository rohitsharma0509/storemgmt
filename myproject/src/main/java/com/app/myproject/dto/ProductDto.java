package com.app.myproject.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
	private Long id;
	
	private String code;

	private String name;
	
	private String brandName;

	private Integer quantity;
	
	private Integer alertQuantity;
	
	private Double purchasePrice;

	private Double perProductPrice;
	
	private MultipartFile image;
	
	private String base64Image;
	
	private Long categoryId;
	
	private Integer availableQuantity;
	
	/*private Set<Purchase> purchases = new HashSet<>();*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getAlertQuantity() {
		return alertQuantity;
	}

	public void setAlertQuantity(Integer alertQuantity) {
		this.alertQuantity = alertQuantity;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getPerProductPrice() {
		return perProductPrice;
	}

	public void setPerProductPrice(Double perProductPrice) {
		this.perProductPrice = perProductPrice;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", code=" + code + ", name=" + name
				+ ", brandName=" + brandName + ", quantity=" + quantity
				+ ", alertQuantity=" + alertQuantity + ", purchasePrice="
				+ purchasePrice + ", perProductPrice=" + perProductPrice
				+ ", image=" + image + ", base64Image=" + base64Image
				+ ", categoryId=" + categoryId + "]";
	}
	
	/*public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}*/
}
