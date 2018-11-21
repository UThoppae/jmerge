package org.jmerge.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jmerge.service.JMerge;
import org.junit.Before;
import org.junit.Test;

public class JMergeTest {
	
	JMerge jMerge = new JMerge();
	
	Person sourcePerson = null;
	
	Person destPerson = null;
	
	Phone sourceHomePhone = null;
	
	Phone sourceMobilePhone  = null;
	
	Phone destHomePhone = null;
	
	Phone destMobilePhone  = null;
	
	
	@Before
	public void setUp() {
		
		
		sourcePerson = new Person();
		sourcePerson.setFirstName("Bruce");
		sourcePerson.setLastName("Lee");
		
		Address sourceAddress = new Address();
		sourceAddress.setCity("Chicago");
		sourceAddress.setState("IL");
		
		sourcePerson.setAddress(sourceAddress);
		//----
		
		destPerson = new Person();
		destPerson.setLastName("Willis");
		
		Address destAddress = new Address();
		destAddress.setCity("Weston");
		destAddress.setState("FL");
		
		destPerson.setAddress(destAddress);
		
		setUpCollection();
		
	}
	
	
	private void setUpCollection() {
		sourceHomePhone = new Phone();
		sourceHomePhone.setNumber(1234567890);
		sourceHomePhone.setType("H");
		
		sourceMobilePhone = new Phone();
		sourceMobilePhone.setNumber(1234567890);
		sourceMobilePhone.setType("M");
		
		
		
		//----------
		
		
		destHomePhone = new Phone();
		destHomePhone.setNumber(12345678);
		destHomePhone.setType("H");
		
		destMobilePhone = new Phone();
		destMobilePhone.setNumber(3345678);
		destMobilePhone.setType("M");
		
		
	}
	

	@Test
	public void testMergePerson() throws Exception {
		
		
		
		
		jMerge.merge(sourcePerson, destPerson);
		
		assertTrue(sourcePerson.getFirstName().equals(destPerson.getFirstName()));
		assertTrue(sourcePerson.getLastName().equals(destPerson.getLastName()));
		
		assertTrue(sourcePerson.getAddress().getCity().equals(destPerson.getAddress().getCity()));
		assertTrue(sourcePerson.getAddress().getState().equals(destPerson.getAddress().getState()));
		
		
	}
	
	
	@Test
	public void testMergeList() throws Exception {
		
		List<Phone> sourcePhones = new ArrayList<>();
		sourcePhones.add(sourceHomePhone);
		sourcePhones.add(sourceMobilePhone);
		sourcePerson.setPhones(sourcePhones);
		
		List<Phone> destPhones = new ArrayList<>();
		destPhones.add(destHomePhone);
		destPhones.add(destMobilePhone);
		destPerson.setPhones(destPhones);
		
		
		
		jMerge.merge(sourcePerson, destPerson);
		
		Phone sourcePhone = sourcePerson.getPhones().get(0);
		
		Phone destPhone = destPerson.getPhones().get(0);
		
		assertEquals(sourcePhone.getNumber(),destPhone.getNumber());
		
		assertEquals(sourcePhone.getType(),destPhone.getType());
		
	}
	
	@Test
	public void testMergeSet() throws Exception {
		
		Set<Phone> sourcePhones = new HashSet<>();
		sourcePhones.add(sourceHomePhone);
		sourcePhones.add(sourceMobilePhone);
		sourcePerson.setPhonesSet(sourcePhones);
		
		Set<Phone> destPhones = new HashSet<>();
		destPhones.add(destHomePhone);
		destPhones.add(destMobilePhone);
		destPerson.setPhonesSet(destPhones);
		
		
		
		jMerge.merge(sourcePerson, destPerson);
		
		Phone sourcePhone = (Phone)sourcePerson.getPhonesSet().toArray()[0];
		
		Phone destPhone = (Phone)destPerson.getPhonesSet().toArray()[0];
		
		assertEquals(sourcePhone.getNumber(),destPhone.getNumber());
		
		assertEquals(sourcePhone.getType(),destPhone.getType());
		
	}


}
