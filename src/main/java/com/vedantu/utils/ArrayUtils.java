package com.vedantu.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ArrayUtils {
	 public static <E extends Object> List<E> toList(E[] array) {
	        
	        return null != array ? Arrays.asList(array) : new ArrayList<>();
	    }
	    
	    public static <E extends Object> boolean isEmpty(List<E> list) {
	        return null == list || list.isEmpty();
	    }
	    
	    public static <E extends Object> boolean isNotEmpty(List<E> list) {
	        return !isEmpty(list);
	    }
	    
	    public static <E extends Object> boolean isEmpty(Set<E> list) {
	        return null == list || list.isEmpty();
	    }
	    
	    public static <E extends Object> boolean isNotEmpty(Set<E> list) {
	        return !isEmpty(list);
	    }    

}
