package cz.muni.fi.pa165.languageschoolweb.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.servlet.ServletInputStream;

/**
 *
 * @author kelnar
 */
public class ApiHelper {
	
	public static boolean isNoArgument(String pathInfo) {
		if ( (pathInfo == null) || ("/".equals(pathInfo)) ) {
			return true;
		}
		return false;
	}
	
	public static String getFirstArg(String pathInfo) {
		String part[] = pathInfo.split("/");
		return part[1];
	}
	
	public static boolean isNumeric(String str)  {  
	  try {  
		double d = Long.valueOf(str);  
	  } catch(NumberFormatException nfe) {  
		return false;  
	  }  
	  return true;  
	}
	
	
	public static String convertStreamToString(ServletInputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
		  sb.append(line + "\n");
		}
		is.close();
		return sb.toString();
	 }
	
}
