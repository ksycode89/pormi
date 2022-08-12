package com.promi.member;

import java.sql.Date;

//.id   - pk
//2.pw
//3.name
//3.구독정보
//4.구매목록
//4.
//5.type (0.관리자 1.소비자)


public class Member {
	private String consumerId;
	private String consumerPw;
	private String consumerName;
	private int test_sub;
	private int roles;
	private String subDay;
	private int delivery;
	private int totlaSpend;
	private Date checkDate;




	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Member() {
		
	}

	public void setTotlaSpend(int totlaSpend) {
		this.totlaSpend = totlaSpend;
	}
	
	public int getTotlaSpend() {
		return totlaSpend;
	}
	
	public int getDelivery() {
		return delivery;
	}

	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}


	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getConsumerPw() {
		return consumerPw;
	}

	public void setConsumerPw(String consumerPw) {
		this.consumerPw = consumerPw;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public int gettest_sub() {
		return test_sub;
	}

	public void settest_sub(int test_sub) {
		this.test_sub = test_sub;
	}

	public int getRoles() {
		return roles;
	}

	public void setRoles(int roles) {
		this.roles = roles;
	}

	public String getSubDay() {
		return subDay;
	}

	public void setSubDay(String subDay) {
		this.subDay = subDay;
	}
	
	@Override
	public String toString() {
		int i = (int)(Math.random()*99)+1;
		String roless = null;
		if(this.roles==1) {
			roless="관리자";
		}else {roless="일반 회원";}
		return "회원id : " + consumerId + " | 회원 비밀번호 : " + "*&"+i+"^@"+i + " | 회원 이름 : " + consumerName
				+" | 회원 구분 : " + roless ;
	}
	
	
	
	
}
