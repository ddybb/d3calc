package ddybb.d3calc.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class D3API {
	
	private static URL url;
	private static HttpURLConnection conn;
	private static BufferedReader rd;
	private static ObjectMapper mapper = new ObjectMapper();
	
	
	public static Map<String, Object> getProfile(String name, String region) {
		
		try {
			url = new URL("http://" + region +".battle.net/api/d3/profile/" + name +"/");
			conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        Map<String, Object> profiledata = mapper.readValue(rd, Map.class);
	        rd.close();
	        return profiledata;
		} catch (Exception e) {
			//handle incorrect battletags
		}
		return null;
	}
	
	public static Map<String, Object> getHero(String name, String region, int heroIndex) {
		
		try {
			url = new URL("http://" + region +".battle.net/api/d3/profile/" + name +"/hero/" + heroIndex);
			conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        Map<String, Object> herodata = mapper.readValue(rd, Map.class);
	        rd.close();
	        return herodata;
		} catch (Exception e) {
			//handle incorrect battletags
		}
		return null;
	}
	
	public static Map<String, Object> getItem(String region, String item) {
		
		try {
			url = new URL("http://" + region +".battle.net/api/d3/data/" + item);
			conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        Map<String, Object> itemdata = mapper.readValue(rd, Map.class);
	        rd.close();
	        return itemdata;
		} catch (Exception e) {
			//handle incorrect battletags
		}
		return null;
	}

}
