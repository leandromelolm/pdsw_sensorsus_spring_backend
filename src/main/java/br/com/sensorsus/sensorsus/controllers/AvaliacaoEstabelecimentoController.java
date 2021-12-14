package br.com.sensorsus.sensorsus.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sensorsus.sensorsus.dto.AvaliacaoEstabelecimentoNewDTO;
import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;
import br.com.sensorsus.sensorsus.services.AvaliacaoEstabelecimentoService;

@RestController
@RequestMapping(value = "/avaliacoesestabelecimentos")
public class AvaliacaoEstabelecimentoController {

	@Autowired
	private AvaliacaoEstabelecimentoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		AvaliacaoEstabelecimento obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AvaliacaoEstabelecimento>> findAll() {
		List<AvaliacaoEstabelecimento> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('STANDARD')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody AvaliacaoEstabelecimentoNewDTO objDto){
		AvaliacaoEstabelecimento obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdAvaliacao()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}

/*
 * 
 * JSON USANDO POST /avaliacoesestabelecimentos/new
{    
    "descricao": "TESTE JSON POST inserindo uma nova avaliação no estabelecimento ",
    "classificacao": "4.1",
    "usuarioId": "2",
    "estabelecimentoId": "5"  
}
 * 
 * */