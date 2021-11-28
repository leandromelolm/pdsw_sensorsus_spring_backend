package br.com.sensorsus.sensorsus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sensorsus.sensorsus.dto.AvaliacaoServicoNEWDTO;
import br.com.sensorsus.sensorsus.services.AvaliacaoServicoNEWService;

@RestController
@RequestMapping(value = "/avaliacoesservico")
public class AvaliacaoServicoNEWController {

	@Autowired
	private AvaliacaoServicoNEWService service;
	
	@GetMapping(value = "/{id}")
	public AvaliacaoServicoNEWDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@GetMapping
	public ResponseEntity<List<AvaliacaoServicoNEWDTO>> findAll(){
		List<AvaliacaoServicoNEWDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
}
