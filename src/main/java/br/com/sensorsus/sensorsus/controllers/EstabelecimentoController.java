package br.com.sensorsus.sensorsus.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sensorsus.sensorsus.controllers.utils.URL;
import br.com.sensorsus.sensorsus.dto.EstabelecimentoAvaliacoesDTO;
import br.com.sensorsus.sensorsus.dto.EstabelecimentoDTO;
import br.com.sensorsus.sensorsus.model.Estabelecimento;
import br.com.sensorsus.sensorsus.services.EstabelecimentoService;

@RestController
@RequestMapping(value = "/estabelecimentos")
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoService service;	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Estabelecimento obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		/*
		 * 
		 * [GET]: exibe um único estabelecimento (passado por id). resultado é ESTABELECIMENTO com todos atributos, ENDEREÇO com todos os atributos, e todas as AVALIACOES com atributos e USUARIO_NICKNAME
		 * Endpoint: /estabelecimento/{id}
		 * 
		 * */
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/avaliacoes", method = RequestMethod.GET)
	public ResponseEntity<List<EstabelecimentoAvaliacoesDTO>> findAvaliacao() {
		List<Estabelecimento> list = service.findAll();
		List<EstabelecimentoAvaliacoesDTO> listAvalicaoDto = list.stream()
				.map(obj -> new EstabelecimentoAvaliacoesDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listAvalicaoDto);
		/*
		 * 
		 * [GET]: lista todos os estabelecimentos com ID, NOME, CODCNES, AVALIACOES com atributos e USUARIO_NICKNAME - Usando DTO
		 * Endpoint: /estabelecimento/avaliacoes		 * 
		 * 
		 * @PreAuthorize("hasAnyRole('ADMIN')") //Autorização de endpoint para perfil especifico
		 * 
		 * */
		
	}

	@RequestMapping(value = "/enderecos" ,method = RequestMethod.GET)
	public ResponseEntity<List<EstabelecimentoDTO>> findAll() {
		List<Estabelecimento> list = service.findAll();
		List<EstabelecimentoDTO> listDto = list.stream().map(obj -> new EstabelecimentoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		/*
		 * 
		 * [GET]: lista todos estabelecimentos com ENDERECO - Uso de DTO
		 * Endpoint: /estabelecimentos/enderecos
		 * 
		 * */
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Estabelecimento>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);

		Page<Estabelecimento> list = service.search(nomeDecoded, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
		/*
		 * 
		 * [GET]:estabelecimentos com Paginação e Pesquisa - resultado é ESTABELECIMENTO com todos atributos, ENDEREÇO com todos os atributos, e todas as AVALIACOES com atributos e USUARIO_NICKNAME
		 * Endpoint: /estabelecimentos/?nome=UPA // O exemplo pesquisa todos os estabelecimentos que tem uma sequência string com UPA
		 * Endpoint: /estabelecimentos/?page=0 // O exemplo retorna a primeira página
		 * Endpoint: /estabelecimentos	// exibe em ordem alfabética a quantidade de elementos(estabelecimentos) atribuidos em linesPerPage. É exibido a page 0, ou seja, primeira página
		 * 
		 * */
	}

}
