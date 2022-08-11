package com.promi.subscript;

import java.sql.Date;

public class Subscript {
	private String consumerId;
	private String productsName;
	private int productNum;
	private int productSpend;
	private Date buyDate;
	private int delivery;
	private int orderNumber;

	public Subscript() {

	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getConsumerId() {
		return consumerId;
	}

	

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getProductsName() {
		return productsName;
	}

	public void setProductsName(String productsName) {
		this.productsName = productsName;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public int getProductSpend() {
		return productSpend;
	}

	public void setProductSpend(int productSpend) {
		this.productSpend = productSpend;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public int getDelivery() {
		return delivery;
	}

	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "주문번호 : "+orderNumber +"\n상품명  : " + productsName + ", 구매 갯수 : " + productNum + ", 구매 가격 : " + productSpend + ", 구매일 : "
				+ buyDate + "]";
	}

}
