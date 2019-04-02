package com.vedantu.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class StringUtils {

    public static final String EMPTY = "";

    public static boolean isEmpty(String s) {

        return null == s || EMPTY.equals(s.trim());
    }

    public static boolean isNotEmpty(String s) {

        return !isEmpty(s);
    }

    public static String toLowerCase(String s) {

        return isEmpty(s) ? EMPTY : s.toLowerCase();
    }

    public static String defaultIfEmpty(String s) {

        return isEmpty(s) ? EMPTY : s;
    }

    public static String captialize(String s) {

        return isEmpty(s) ? EMPTY : (s.substring(0, 1).toUpperCase() + s
                .substring(1).toLowerCase());
    }

    public static <E extends Object> String collectionSize(
            Collection<E> collection) {

        return null != collection ? String.valueOf(collection.size()) : "null";
    }

    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(Object[] array, String separator, int startIndex,
            int endIndex) {
        if (array == null) {
            return null;
        }
        int noOfItems = (endIndex - startIndex);
        if (noOfItems <= 0) {
            return EMPTY;
        }

        StringBuilder buf = new StringBuilder(noOfItems * 16);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String join(Iterator<?> iterator, char separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return first.toString();
        }

        // two or more elements
        StringBuilder buf = new StringBuilder(256); // Java default is 16,
        // probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }

        return buf.toString();
    }

    static final String AB = "123456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    static final String AB_LOWER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    //TODO: zero
    static final String NM = "123456789";
    static Random rnd = new Random();

    public static String randomAlphaNumericString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public static String randomNumericString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(NM.charAt(rnd.nextInt(NM.length())));
        }
        return sb.toString();
    }

    public static String randomAlphaNumericStringWithLowerCase(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB_LOWER.charAt(rnd.nextInt(AB_LOWER.length())));
        }
        return sb.toString();
    }

    public static List<String> isValidAdminPassword(String passwordhere) {

        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        Pattern vedantuCharPatten = Pattern.compile("vedantu", Pattern.CASE_INSENSITIVE);
        List<String> errorList = new ArrayList<>();

        if (passwordhere.length() < 8) {
            errorList.add("Password lenght must have alleast 8 character !!");
        }
        if (!specailCharPatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one specail character !!");
        }
        if (!UpperCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one uppercase character !!");
        }
        if (!lowerCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one lowercase character !!");
        }
        if (!digitCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have atleast one digit character !!");
        }
        if (vedantuCharPatten.matcher(passwordhere).find()) {
            errorList.add("Password must not contain word vedantu !!");
        }
        return errorList;
    }

    public static void main(String[] args) {
        String as = "ajitHiuwydiew1#Vedantu";
        String as2 = "ajitHiuwydiew1#Ved3ntu";
        String as3 = "ajitHiuwydiew1#VeDantu";
        List<String> er = new ArrayList<>();
        System.err.println(">> " + isValidAdminPassword(as));
        System.err.println(">> " + isValidAdminPassword(as2));
        System.err.println(">> " + isValidAdminPassword(as3));
        System.err.println(">>?? " + er);
    }
}

