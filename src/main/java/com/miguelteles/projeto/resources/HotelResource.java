package com.miguelteles.projeto.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguelteles.projeto.entities.Hotel;
import com.miguelteles.projeto.services.HotelService;

@RestController
@RequestMapping
public class HotelResource {
	
	@Autowired
	private HotelService service;
		
	//Listagem de hotéis
	@GetMapping(value="/search/{destination}/{start}/{end}/{occupancy}")	
	private ResponseEntity<List<Hotel>> listaHoteis(
			@PathVariable Integer destination,@PathVariable String start, @PathVariable String end, @PathVariable String occupancy){		
		List<Hotel> hoteis = service.pegaListaHoteis(destination, start, end, occupancy);			
		return ResponseEntity.ok().body(hoteis);
	}	
	
	//Detalhe dos hotéis
	@GetMapping(value="/hotel/{keyDetail}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> detalheHotel(@PathVariable String keyDetail){
		String detalhes = service.pegaDetalheHotel(keyDetail);		
		return ResponseEntity.ok().body(detalhes);
	}
}
