package com.promi.product;
//1.name
//2.prise
//3.kind
//4.grade(평점)-(4점이하면 삭제? 고민)
//5.explain

public class Product {
	private String productName;
	private int ProductPrice;
	private String productKind;
	private int productGrade;
	private String productExplain;
	private int productTotalM;
	
	public int getProductTotalM() {
		return productTotalM;
	}

	public void setProductTotalM(int productTotalM) {
		this.productTotalM = productTotalM;
	}

	public Product() {
		
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return ProductPrice;
	}

	public void setProductPrice(int productPrice) {
		ProductPrice = productPrice;
	}

	public String getProductKind() {
		return productKind;
	}

	public void setProductKind(String productKind) {
		this.productKind = productKind;
	}

	public int getProductGrade() {
		return productGrade;
	}

	public void setProductGrade(int productGrade) {
		this.productGrade = productGrade;
	}

	public String getProductExplain() {
		return productExplain;
	}

	public void setProductExplain(String productExplain) {
		this.productExplain = productExplain;
	}

	@Override
	public String toString() {
		return "제품명  : " + productName + " | 제품가격 : " + ProductPrice + " | 제품종류 : " + productKind
				+ " | 제품 평점 : " + productGrade + "|\n제품설명 : " + productExplain ;
	}
	
	
	
}
