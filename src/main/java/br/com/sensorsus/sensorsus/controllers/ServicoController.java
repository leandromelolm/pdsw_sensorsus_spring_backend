package br.com.sensorsus.sensorsus.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sensorsus.sensorsus.dto.ServicoAvaliacoesDTO;
import br.com.sensorsus.sensorsus.dto.ServicoDTO;
import br.com.sensorsus.sensorsus.model.Servico;
import br.com.sensorsus.sensorsus.services.ServicoService;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoController {

	@Autowired
	private ServicoService service;	

	@RequestMapping(value = "/{id}/avaliacoes", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		ServicoAvaliacoesDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		/*
		 * GET Endpoint: /servicos/{id}/avaliacoes
		 * Exibe nome do serviço e estabelecimento que presta o serviço do {id} informado. É informado a avaliações feitas pelos usuários desse serviço 
		 * 
		 * ID_SERVICO, NOME_SERVICO, NOME_ESTABELECIMENTO, AVALIACOES_SERVICO
		 * */		
	}
	
	@RequestMapping(value = "/avaliacoes", method = RequestMethod.GET)
	public ResponseEntity<List<ServicoAvaliacoesDTO>> findAvaliacao() {
		List<Servico> list = service.findAll();
		List<ServicoAvaliacoesDTO> listServAvalicaoDto = list.stream().map(obj -> new ServicoAvaliacoesDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listServAvalicaoDto);
		/*
		 * GET Endpoint: /servicos/avaliacoes
		 * lista todos serviços e suas avaliações - Usando DTO
		 * 
		 * exibe: ID_SERVICO, NOME_SERVICO, NOME_ESTABELECIMENTO, AVALIACOES_SERVICO		 
		 * 
		 */
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ServicoDTO>> findAll() {
		List<Servico> list = service.findAll();
		List<ServicoDTO> listDto = list.stream().map(obj -> new ServicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		/*
		 * GET Endpoint: /servicos
		 * lista todos serviços e seus estabelecimentos
		 * 
		 * exibe: ID, NOME, ESTABELECIMENTO	 
		 * 
		 */
	}
}
