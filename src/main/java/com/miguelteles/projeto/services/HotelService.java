package com.miguelteles.projeto.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.miguelteles.projeto.entities.Hotel;
import com.miguelteles.projeto.repository.ApiAuthorization;
import com.miguelteles.projeto.resources.util.Date;
import com.miguelteles.projeto.resources.util.JsonParse;
import com.miguelteles.projeto.services.exception.AuthenticationException;
import com.miguelteles.projeto.services.exception.ConnectionException;
import com.miguelteles.projeto.services.exception.InvalidDataException;

@Service
public class HotelService {

	private final String baseUrl = "http://api.infotravel.com.br/api/v1";
	private String output="";	
	private String token;
	
	public HotelService() {
		try {
			token = "Bearer " + ApiAuthorization.getNewToken().trim();
		}  catch(AuthenticationException e1) {
			throw new AuthenticationException(e1.getMessage());
		}
	}
			
	//Cria a conexão com a API e retorna a conexão. Método para facilitar esse processo que é utilizado mais de uma vez.
	private String fazRequisicao(URL url) {	
		
		HttpURLConnection conn=null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", token);
			
			if(conn.getResponseCode() != 200) {									
				throw new ConnectionException(conn.getResponseCode()+"");
			}			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			output = br.lines().collect(Collectors.joining());
			conn.disconnect();
			return output;
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e1) {
			e1.printStackTrace();
		} 
		
		return output;
		
	}
	
	//retorna a lista de hoteis
	public List<Hotel> pegaListaHoteis(Integer destination, String start, String end, String occupancy) {		
		String query_url = baseUrl+String.format("/avail/hotel?destination=%d&start=%s&end=%s&occupancy=%s", destination, start, end, occupancy);	
		try {	
			Date.dataValida(start);
			Date.dataValida(end);
			return JsonParse.listaHoteisDeJson(fazRequisicao(new URL(query_url)));
		} catch (MalformedURLException e) {				
			e.printStackTrace();
		} catch(InvalidDataException e1) {
			throw new InvalidDataException(e1.getMessage());
		}
		
		return null;		
	}
		
	//retorna os detalhes do hotel
	public String pegaDetalheHotel(String keyDetail) {
		String query_url = baseUrl+String.format("/utility/hotelDetail/%s", keyDetail);		
		try {
			return fazRequisicao(new URL(query_url));			
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
