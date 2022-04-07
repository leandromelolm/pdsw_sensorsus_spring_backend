package br.com.sensorsus.sensorsus.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sensorsus.sensorsus.controllers.utils.URL;
import br.com.sensorsus.sensorsus.dto.AvaliacaoEstabelecimentoDTO;
import br.com.sensorsus.sensorsus.dto.AvaliacaoEstabelecimentoNewDTO;
import br.com.sensorsus.sensorsus.dto.EstabelecimentoAvaliacoesDTO;
import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;
import br.com.sensorsus.sensorsus.services.AvaliacaoEstabelecimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api/avaliacoes")
@Api("Api de avaliações de estabelecimento")
public class AvaliacaoEstabelecimentoController {

	@Autowired
	private AvaliacaoEstabelecimentoService service;
	
//	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		AvaliacaoEstabelecimento obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		// [GET] route: http://localhost:8080/api/avaliacoes/{id}		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/avaliacaoestabelecimento/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		AvaliacaoEstabelecimentoDTO aeDto = service.findById(id);
		return ResponseEntity.ok().body(aeDto);
		// [GET] http://localhost:8080/api/avaliacoes/avaliacaoestabelecimento/{id}
	}
	
	@RequestMapping(value = "/estabelecimento", method = RequestMethod.GET)
	public ResponseEntity<Page<AvaliacaoEstabelecimentoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="20") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dataCriacao") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		Page<AvaliacaoEstabelecimento> list = service.search(nomeDecoded, page, linesPerPage, orderBy, direction);
		Page<AvaliacaoEstabelecimentoDTO> listDto = list.map(obj -> new AvaliacaoEstabelecimentoDTO(obj));
		return ResponseEntity.ok().body(listDto);
		// [GET] route: http://localhost:8080/api/avaliacoes/estabelecimento   // exibe as avaliações de estabelecimento da página 0
		// [GET] http://localhost:8080/api/avaliacoes/estabelecimento/?nome={String}   // pesquisa por nome do estabelecimento
		// [GET] http://localhost:8080/api/avaliacoes/estabelecimento/?page={page}   // número da página (padrão é 0)
		// [GET] http://localhost:8080/api/avaliacoes/estabelecimento/?nome={String}&page={page}		
	}
	
	
	// Pesquisa de avaliação por usuario para exibir na página MyRatings do frontend (usuario visualiza suas avaliações)
	@RequestMapping(value = "/usuarioid", method = RequestMethod.GET)
	public ResponseEntity<Page<AvaliacaoEstabelecimentoDTO>> findRatingUser(
			@RequestParam(value="id", defaultValue="") String id,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="20") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dataCriacao") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Integer idInteger = Integer.parseInt(id);
		Page<AvaliacaoEstabelecimento> list = service.searchIdUsuario(idInteger, page, linesPerPage, orderBy, direction);
		Page<AvaliacaoEstabelecimentoDTO> listDto = list.map(obj -> new AvaliacaoEstabelecimentoDTO(obj));
		return ResponseEntity.ok().body(listDto);
		// [GET] localhost:8080/api/avaliacoes/usuarioid/?id=1
	}
	
	@RequestMapping(value = "/estabelecimento/id", method = RequestMethod.GET)
	public ResponseEntity<Page<AvaliacaoEstabelecimentoDTO>> findIdPage(
			@RequestParam(value="id", defaultValue="") String id, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="20") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dataCriacao") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Integer idInteger = Integer.parseInt(id);
		Page<AvaliacaoEstabelecimento> list = service.searchIdEstabelecimento(idInteger, page, linesPerPage, orderBy, direction);
		Page<AvaliacaoEstabelecimentoDTO> listDto = list.map(obj -> new AvaliacaoEstabelecimentoDTO(obj));
		return ResponseEntity.ok().body(listDto);
		// [GET] http://localhost:8080/api/avaliacoes/estabelecimento/id/?id=1
		// [GET] http://localhost:8080/api/avaliacoes/estabelecimento/id/?id=2&page=0	
	}	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AvaliacaoEstabelecimentoDTO>> findAll() {
		List<AvaliacaoEstabelecimento> list = service.findAll();
		List<AvaliacaoEstabelecimentoDTO> listAvaliacaoDto = list.stream()
				.map(obj -> new AvaliacaoEstabelecimentoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listAvaliacaoDto);
		// [GET] http://localhost:8080/api/avaliacoes/	
	}
	
	@PreAuthorize("hasAnyRole('STANDARD')")	
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody AvaliacaoEstabelecimentoNewDTO objDto){
		service.update(objDto);
		return ResponseEntity.noContent().build();	
		// [PUT] http://localhost:8080/api/avaliacoes
	}	
	
	@ApiOperation(value = "POST Avaliação", nickname = "Cria avaliação de um estabelecimento")
	@PreAuthorize("hasAnyRole('STANDARD')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)	
	public ResponseEntity<Void> insert(@RequestBody AvaliacaoEstabelecimentoNewDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(dto.getAvaliacaoId()).toUri();
		return ResponseEntity.created(uri).build();
		// [POST] http://localhost:8080/api/avaliacoes/new
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AvaliacaoEstabelecimentoDTO> delete(@PathVariable Integer id){
		service.delete(id); //pega o id e passa como argumento para o método delete do Service
		return ResponseEntity.noContent().build(); 	
	}
}




/*
 *

@RequestMapping(value = "/new", method = RequestMethod.POST)

 [POST]: cria uma avaliação de um estabelecimento
 http://localhost:8080/api/avaliacoes/new
 
 Formato do JSON, exemplo:
 
	{
		"descricao": "TESTE JSON POST inserindo uma nova avaliação no estabelecimento ",
		"classificacao": "4.1",
		"usuarioEmail":"test5test@test.com",
		"estabelecimentoId": "12"
	}
	 
 OBS. Para criar uma avaliação é preciso inserir no Headers Authorization o token recebido na autenticação. Na criação da avaliação é necessário o usuario passar seu email de login.
 
 @PreAuthorize("hasAnyRole('STANDARD')") //Autorização de endpoint para perfil especifico
 @PreAuthorize("hasAnyRole('ADMIN')") //Autorização de endpoint para perfil especifico - ADMIN: apenas usuário admin poderá ter acesso ao endpoint 
 
*
* */