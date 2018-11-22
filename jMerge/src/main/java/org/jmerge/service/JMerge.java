package org.jmerge.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  To merge two objects properties
 * 
 * @author uthoppae
 *
 */
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
	
	/**
	 * This method is used to merge all the properties from source to destination.
	 * 	
	 * @param source
	 * @param dest
	 * @throws IllegalAccessException
	 */
	public void merge(Object source, Object dest) throws Exception {
		
		Class<?> sourceClass = source.getClass();
		Field[] sourceFields = sourceClass.getDeclaredFields();
		
		Class<?> destClass = dest.getClass();
		Field[] destFields = destClass.getDeclaredFields();
		
		for(Field sourceField : sourceFields) {
			
			sourceField.setAccessible(true);
			
			if(sourceField.get(source) == null ) {
				continue;
			}
			
			for(Field destField : destFields) {
				
				destField.setAccessible(true);
				
				if(sourceField.getName().equals(destField.getName())) {
					
					mergeProperties(sourceField, destField, source, dest);
					break;
				}
			}
			
		}
		
	}
	
	private void mergeProperties(Field sourceField,Field destField,Object source, Object dest) throws Exception  {
		
			
			if(sourceField.getType().isPrimitive() || sourceField.getType() == String.class) {
				destField.set(dest, sourceField.get(source));
			}else if(Map.class.isAssignableFrom(sourceField.getType())) {
				
				mergeMap(sourceField, destField, source, dest);
				
			}else if(Collection.class.isAssignableFrom(sourceField.getType())) {
				
				mergeCollections(sourceField, destField, source, dest);
			}
			else {
				merge(sourceField.get(source), destField.get(dest));
			}
			
	}
	
	private void mergeCollections(Field sourceField,Field destField,Object source, Object dest) throws Exception {
		Collection<?> sourceObjectList = (Collection<?>)sourceField.get(source);
		Object[] sourceObjs = sourceObjectList.toArray();
		Collection<?> destObjectList = (Collection<?>)destField.get(dest);
		Object[] destObjs = destObjectList.toArray();
		for(int i=0;i<sourceObjs.length;i++) {
			merge(sourceObjs[i], destObjs[i]);
		} 
	}
	
	private void mergeMap(Field sourceField,Field destField,Object source, Object dest) throws Exception {
		Map<?, ?> sourceMap = (Map<?, ?>)sourceField.get(source);
		Map<?, ?> destMap = (Map<?, ?>)destField.get(dest);
		
		Set<?> keys = sourceMap.keySet();
		for(Object key : keys) {
			merge(sourceMap.get(key), destMap.get(key));
		}
	}

}
