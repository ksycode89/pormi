package com.promi.event;

import java.sql.Date;

public class Event {
	 private int grade;
	 private int eventNum;
	 private int count;
	 private String eventTitle;
	 private String eventContents;
	 private Date eventDay;
	 private String consumerId;
	 private int roles;
	 
	 public Event() {
		 
	 }
	 
	 
	 
	 
	 public int getRoles() {
		return roles;
	}




	public void setRoles(int roles) {
		this.roles = roles;
	}




	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}



	 
	
	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public int getEventNum() {
		return eventNum;
	}


	public void setEventNum(int eventNum) {
		this.eventNum = eventNum;
	}


	public String getEventTitle() {
		return eventTitle;
	}


	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}


	public String getEventContents() {
		return eventContents;
	}


	public void setEventContents(String eventContents) {
		this.eventContents = eventContents;
	}


	public Date getEventDay() {
		return eventDay;
	}


	public void setEventDay(Date eventDay) {
		this.eventDay = eventDay;
	}


	public String getConsumerId() {
		return consumerId;
	}


	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}





	@Override
	public String toString() {
		return "Event [grade=" + grade + ", eventContents=" + eventContents + ", eventDay=" + eventDay
				+ ", consumerId=" + consumerId + "]";
	}
	 
	 

}
