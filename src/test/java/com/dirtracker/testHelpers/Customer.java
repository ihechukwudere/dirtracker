package com.dirtracker.testHelpers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

	private long cusId;
	private String firstName;
	private String lastName;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getCusId() {
		return cusId;
	}
	public void setCusId(long cusId) {
		this.cusId = cusId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
