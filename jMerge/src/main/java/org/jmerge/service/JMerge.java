package org.jmerge.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;



public class JMerge {
	
	private static List<String> primitiveTypes = new ArrayList<>();
	
	static {
		primitiveTypes.add("String");
		primitiveTypes.add("byte");
		primitiveTypes.add("short");
		primitiveTypes.add("int");
		primitiveTypes.add("long");
		primitiveTypes.add("float");
		primitiveTypes.add("double");
	}
	
	
	
	public void merge(Object source, Object dest) throws IllegalAccessException {
		
		
		
		Class sourceClass = source.getClass();
		Field[] sourceFields = sourceClass.getDeclaredFields();
		
		Class destClass = dest.getClass();
		Field[] destFields = destClass.getDeclaredFields();
		
		for(Field sourceField : sourceFields) {
			
			sourceField.setAccessible(true);
			
			for(Field destField : destFields) {
				
				destField.setAccessible(true);
				
				if(sourceField.getName().equals(destField.getName())) {
					
					if(sourceField.getType().isPrimitive() || sourceField.getType() == String.class) {
						destField.set(dest, sourceField.get(source));
					}else {
						merge(sourceField.get(source), destField.get(dest));
					}
					break;
				}
			}
			
		}
		
	}

}
