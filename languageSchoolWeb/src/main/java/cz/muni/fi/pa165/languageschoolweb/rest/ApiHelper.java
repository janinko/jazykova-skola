/*
 * Copyright 2012 kelnar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
		double d = Double.parseDouble(str);  
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
