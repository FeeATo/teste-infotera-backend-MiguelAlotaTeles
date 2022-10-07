package com.miguelteles.projeto.entities;

import java.io.Serializable;

public class Hotel implements Serializable{	   
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String keyDetail;
	private String name;
	private String checkIn;
	private String checkout;
	
	public Hotel() {}
	
	public Hotel(Integer id, String keyDetail, String name, String checkIn, String checkout) {		
		this.id = id;
		this.keyDetail = keyDetail;
		this.name = name;
		this.checkIn = checkIn;
		this.checkout = checkout;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyDetail() {
		return keyDetail;
	}

	public void setKeyDetail(String keyDetail) {
		this.keyDetail = keyDetail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}		
}
