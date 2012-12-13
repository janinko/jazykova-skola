package cz.muni.fi.pa165.languageschool.cli.iface;

import com.google.gson.Gson;
import cz.muni.fi.pa165.languageschool.api.Language;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
class Helper {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	final static String ESC = "\033[";
	
	public static void clear(){
		System.out.print(ESC + "2J");
	}

	public static Object getResponse(String message, Object ... expected){
		boolean cislo = false;
		boolean string = false;
		boolean language = false;
		boolean languages = false;
		HashSet<String> stringy = new HashSet<String>();
		for(Object o : expected){
			if(o.equals(Integer.class)){
				cislo = true;
			}else if(o.equals(String.class)){
				string = true;
			}else if(o.equals(Language.class)){
				language = true;
			}else if(o.equals(Language[].class)){
				languages = true;
			}else if(o instanceof String){
				stringy.add((String) o);
			}
		}

		System.out.print(message);
		int repeat=15;
		while (repeat > 0){
			System.out.print(": ");
			try {
				String line = input.readLine();
				if(stringy.contains(line)){
					return line;
				}
				if(cislo){
					try{
						return Integer.valueOf(line);
					}catch(NumberFormatException ex){
						// nothing
					}
				}
				if(language){
					try{
						return Language.valueOf(line);
					}catch(IllegalArgumentException ex){
						// nothing
					}
				}
				if(languages) {
					try {
						Set<Language> langs = new HashSet<Language>();
						for (String l : line.split(", *")) {
							langs.add(Language.valueOf(l));
						}
						Language[] ll = new Language[langs.size()];
						return langs.toArray(ll);
					} catch (IllegalArgumentException ex) {
						// nothing
					}
				}
				if(string){
					return line;
				}
			} catch (IOException ex) {
				Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
				Runtime.getRuntime().exit(1);
			}
		}
		return null;
	}

	public static <T> T retrieve(Class<T> type, String urlPart){
		Gson gson = new Gson();
		T ret = null;
		try {
			URL url= new URL("http://localhost:8080/pa165/api" + urlPart);
			StringWriter writer = new StringWriter();
			IOUtils.copy(url.openStream(), writer);
			String json = writer.toString();
			ret= gson.fromJson(json, type);
		} catch (MalformedURLException ex) {
			//Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			//Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ret;
	}
}
