package com.actorsaccess.application.system;

public class AAUtil {
	  public static boolean compare;
	  private final static int CONST_ZERO = '0';

	  public static boolean isNullOrEmpty(String s) {
	    return s == null || s.isEmpty();
	  }

	  public static int stringToPositiveInt(String s, boolean throwException) {
	    return s == null ? 0 : stringToPositiveInt(s, 0, s.length(), throwException);
	  }

	  public static int stringToPositiveInt(String s, int from, int to, boolean throwException) {
	    int n = 0;
	    for (; from < to && n >= 0; from++) {
	      final int d = s.charAt(from) - CONST_ZERO;
	      if (d < 0 || d > 9)
	        break;
	      n = n * 10 + d;
	    }
	    if (from < to || n < 0) {
	      if (throwException)
	        throw new NumberFormatException();
	      else
	        n = -1;
	    }
	    return n;
	  }
}
