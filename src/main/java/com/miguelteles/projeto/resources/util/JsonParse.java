package com.miguelteles.projeto.resources.util;

import java.util.ArrayList;
import java.util.List;

import com.miguelteles.projeto.entities.Hotel;

//Esta classe útil auxilia em operações com JSON

public final class JsonParse {

	//Criei estes métodos porque não achei nenhuma biblioteca que me auxiliasse na criação de um objeto com apenas alguns valores da resposta HTTP e não com todos.
		
	//Este método retorna uma lista de hoteis com base em um json string passado como parâmetro. Os hotéis retornados não contêm todas as propriedades recebidas, como foi pedido no problema.
	public static List<Hotel> listaHoteisDeJson(String json){		
		List<Hotel> lista_hoteis = new ArrayList<>();		
		String hoteis[] = json.split("\"hotel\"");		
		
		String nome, checkin, checkout, keyDetail;
		Integer id;
		//começa do 1 porque o 0 é o "{"hotelAvail":[{" da resposta HTTP.
		for(int i = 1; i<hoteis.length; i++) {
			
			nome = getStringValueFromKeyIndex(hoteis[i].indexOf("name"), hoteis[i]);
			id = Integer.parseInt(getIntegerValueFromKeyIndex(hoteis[i].indexOf("id"), hoteis[i]));
			checkin = getStringValueFromKeyIndex(hoteis[i].indexOf("checkIn"), hoteis[i]);
			checkout = getStringValueFromKeyIndex(hoteis[i].indexOf("checkOut"), hoteis[i]);
			keyDetail = getStringValueFromKeyIndex(hoteis[i].indexOf("keyDetail"), hoteis[i]);
			
			lista_hoteis.add(new Hotel(id, keyDetail, nome, checkin,checkout));			
		}
		
		return lista_hoteis;
	}
	
	//Este método pega um valor String com base em sua chave. 
	/*Parâmetros: 
	 * key_index - o index da primeira letra da chave
	 * json - a string json completa
	 */
	public static String getStringValueFromKeyIndex(int key_index, String json) {
		int i = 0;				
		while(!(json.charAt(key_index+i)=='"' && (json.charAt(key_index+i+1)==',' || json.charAt(key_index+i+1)=='}'))) {
			i++;
		}		
		int end_index=key_index+i;		
		
		String keyValue = json.substring(key_index, end_index);
		keyValue = keyValue.replace("\"", "");
		String keyValueArr[] = keyValue.split(":");		
		
		return keyValueArr[1].trim();
	}
	
	//Este método pega um valor Integer com base em sua chave. 
	/*Parâmetros: 
	 * key_index - o index da primeira letra da chave
	 * json - a string json completa
	*/
	public static String getIntegerValueFromKeyIndex(int key_index, String json) {
		int i = 0;			
		while(!(json.charAt(key_index+i)==',')) {
			i++;
		}		
		int end_index=key_index+i;
		
		String keyValue = json.substring(key_index, end_index);
		keyValue = keyValue.replace("\"", "");
		String keyValueArr[] = keyValue.split(":");		
		
		return keyValueArr[1].trim();
	}	
}


