package org.jmerge.service.test;

import static org.junit.Assert.*;

import org.jmerge.service.JMerge;
import org.junit.Test;

public class JMergeTest {
	
	
	
	

	@Test
	public void testMergePerson() throws Exception {
		JMerge jMerge = new JMerge();
		
		Person sourcePerson = new Person();
		sourcePerson.setFirstName("Bruce");
		sourcePerson.setLastName("Lee");
		
		Address sourceAddress = new Address();
		sourceAddress.setCity("Chicago");
		sourceAddress.setState("IL");
		
		sourcePerson.setAddress(sourceAddress);
		
		//----------
		
		Person destPerson = new Person();
		destPerson.setLastName("Willis");
		
		Address destAddress = new Address();
		destAddress.setCity("Weston");
		destAddress.setState("FL");
		
		destPerson.setAddress(destAddress);
		
		
		jMerge.merge(sourcePerson, destPerson);
		
		assertTrue(sourcePerson.getFirstName().equals(destPerson.getFirstName()));
		assertTrue(sourcePerson.getLastName().equals(destPerson.getLastName()));
		
		assertTrue(sourcePerson.getAddress().getCity().equals(destPerson.getAddress().getCity()));
		assertTrue(sourcePerson.getAddress().getState().equals(destPerson.getAddress().getState()));
		
		
	}

}
