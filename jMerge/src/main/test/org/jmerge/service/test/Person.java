package org.jmerge.service.test;

import java.util.List;
import java.util.Set;

public class Person {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Address address;
	
	private List<Phone> phones;

	private Set<Phone> phonesSet;
	

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Set<Phone> getPhonesSet() {
		return phonesSet;
	}

	public void setPhonesSet(Set<Phone> phonesSet) {
		this.phonesSet = phonesSet;
	}
	
	

}
