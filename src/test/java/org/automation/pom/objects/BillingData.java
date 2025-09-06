package org.automation.pom.objects;

public class BillingData {
	private String firstName;
	private String lastName;
	private String adressLineOne;
	private String town;
	private String postalCode;
	private String email;
	public String getFirstName() {
		return firstName;
	}
	public BillingData setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	public String getLastName() {
		return lastName;
	}
	public BillingData setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	public String getAdressLineOne() {
		return adressLineOne;
	}
	public BillingData setAdressLineOne(String adressLineOne) {
		this.adressLineOne = adressLineOne;
		return this;
	}
	public String getTown() {
		return town;
	}
	public BillingData setTown(String town) {
		this.town = town;
		return this;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public BillingData setPostalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public BillingData setEmail(String email) {
		this.email = email;
		return this;
	}
}
