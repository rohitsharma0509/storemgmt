package com.app.myproject.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Long id;
	
	@Column(name = "product_code")
	private String code;

	@Column(name = "product_name")
	private String name;
	
	@Column(name = "brand_name")
	private String brandName;

	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "alert_quantity")
	private Integer alertQuantity;
	
	@Column(name = "purchasePrice")
	private Double purchasePrice;

	@Column(name = "per_product_price")
	private Double perProductPrice;
	
	@Column(name = "image", columnDefinition="longblob")
	private byte[] image;
	
	@JoinColumn(name = "category_id")
	private Long categoryId;
	
	/*@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Purchase> purchases = new HashSet<>();*/

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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name
				+ ", brandName=" + brandName + ", quantity=" + quantity
				+ ", alertQuantity=" + alertQuantity + ", purchasePrice="
				+ purchasePrice + ", perProductPrice=" + perProductPrice
				+ ", image=" + Arrays.toString(image) + ", categoryId="
				+ categoryId + "]";
	}

	/*public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}*/
}