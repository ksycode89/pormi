package com.promi.subscript;

public class Subscript {
	private String consumerId;
	private String incenseN;
	private String teaN;
	private String pillowMistN;
	
	public Subscript() {
		
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getIncenseN() {
		return incenseN;
	}

	public void setIncenseN(String incenseN) {
		this.incenseN = incenseN;
	}

	public String getTeaN() {
		return teaN;
	}

	public void setTeaN(String teaN) {
		this.teaN = teaN;
	}

	public String getPillowMistN() {
		return pillowMistN;
	}

	public void setPillowMistN(String pillowMistN) {
		this.pillowMistN = pillowMistN;
	}

	@Override
	public String toString() {
		return "Subscript [consumerId=" + consumerId + ", incenseN=" + incenseN + ", teaN=" + teaN + ", pillowMistN="
				+ pillowMistN + "]";
	}

	
}
