package br.com.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class Weather {

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://api.wunderground.com/api/1e37ada8b850301c/conditions/lang:BR/q/Brazil/S%C3%A3o%20Paulo.json");
		URLConnection con = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
		StringBuilder response = new StringBuilder();
		String line;
		
		while((line = br.readLine()) != null){
			response.append(line);
		}
		JSONObject json = (JSONObject) JSONValue.parse(response.toString());
		
		JSONObject forecast = (JSONObject) json.get("current_observation");
		
		System.out.println(URLEncoder.encode("São Roque", "UTF-8"));
	}
}

