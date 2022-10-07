package com.miguelteles.projeto.repository;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.miguelteles.projeto.services.exception.AuthenticationException;

//Esta classe é responsável por fazer a autenticação. Seu único método retorna um token para ser usado nas requisições.

public final class ApiAuthorization {
	
	private static final String baseUrl = "http://api.infotravel.com.br/api/v1";
	
	public static String getNewToken(){
		
		String query_url = baseUrl+"/authenticate";
		String json = "{\"username\": \"sandboxws\", \"password\": \"!sdb2022#\", \"client\": \"SANDBOX\", \"agency\": \"2\"}";
		
		try {
			URL url = new URL(query_url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.addRequestProperty("Content-type", "application/json; charset=UTF-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			
			//body da requisição
			OutputStream os = conn.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.close();
			
			InputStream in = new BufferedInputStream(conn.getInputStream());
			String result = IOUtils.toString(in, StandardCharsets.UTF_8);
			
			JsonObject convertedObject = new Gson().fromJson(result, JsonObject.class);
			
			//retorna o token
			return convertedObject.get("accessToken").getAsString();						
		} catch(MalformedURLException e) {
			throw new AuthenticationException(e.getMessage());
		} catch(IOException e1) {
			throw new AuthenticationException(e1.getMessage());
		}		
	}	
}
