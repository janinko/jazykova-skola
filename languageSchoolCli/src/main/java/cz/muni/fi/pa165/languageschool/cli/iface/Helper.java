package cz.muni.fi.pa165.languageschool.cli.iface;

import com.google.gson.Gson;
import cz.muni.fi.pa165.languageschool.api.Language;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
class Helper {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	final static String ESC = "\033[";
	final static String NL = System.getProperty("line.separator");
	final static String apiurl = "http://localhost:8080/pa165/api/";
	
	public static void clear(){
		//System.out.print(ESC + "2J");
		System.out.println("------------------------------");
		System.out.print(NL+NL+NL+NL+NL+NL);
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
			repeat--;
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
				if(string && !line.isEmpty()){
					return line;
				}
			} catch (IOException ex) {
				Helper.log(ex);
				Runtime.getRuntime().exit(1);
			}
		}
		return null;
	}

	public static <T> T read(Class<T> type, String urlPart){
		Gson gson = new Gson();
		T ret = null;
		try {
			URL url= new URL(apiurl + urlPart);
			StringWriter writer = new StringWriter();
			IOUtils.copy(url.openStream(), writer);
			String json = writer.toString();
			ret= gson.fromJson(json, type);
		} catch (MalformedURLException ex) {
			Helper.log(ex);
		} catch (IOException ex) {
			Helper.log(ex);
		}
		return ret;
	}

	public static <T> T create(T object, String urlPart){
		T ret = null;
		Gson gson = new Gson();
		String json = gson.toJson(object);
		try {
			URL url = new URL(apiurl + urlPart);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			Writer writer = new OutputStreamWriter(os);
			writer.append(json);
			IOUtils.closeQuietly(writer);

			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
				StringWriter swriter = new StringWriter();
				IOUtils.copy(connection.getInputStream(), swriter);
				json = swriter.toString();
				ret = gson.fromJson(json, (Class<T>) object.getClass());
			}
		} catch (MalformedURLException ex) {
			Helper.log(ex);
		} catch (IOException ex) {
			Helper.log(ex);
		}
		return ret;
	}

	public static boolean update(Object object, String urlPart){
		Gson gson = new Gson();
		String json = gson.toJson(object);
		try {
			URL url = new URL(apiurl + urlPart);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			Writer writer = new OutputStreamWriter(os);
			writer.append(json);
			IOUtils.closeQuietly(writer);
			return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
		} catch (MalformedURLException ex) {
			Helper.log(ex);
		} catch (IOException ex) {
			Helper.log(ex);
		}
		return false;
	}

	public static boolean delete(long id, String urlPart){
		try {
			URL url = new URL(apiurl + urlPart + "/" + id);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
		} catch (MalformedURLException ex) {
			Helper.log(ex);
		} catch (IOException ex) {
			Helper.log(ex);
		}
		return false;
	}




	private static void log(Exception ex) {
		//Logger.getLogger(Courses.class.getName()).log(Level.SEVERE, null, ex);
		System.out.println("Problém při komunikaci se serverem.");
	}

}
