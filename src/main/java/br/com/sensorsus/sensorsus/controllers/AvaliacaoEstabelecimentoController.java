package br.com.sensorsus.sensorsus.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sensorsus.sensorsus.controllers.utils.URL;
import br.com.sensorsus.sensorsus.dto.AvaliacaoEstabelecimentoDTO;
import br.com.sensorsus.sensorsus.dto.AvaliacaoEstabelecimentoNewDTO;
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
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		AvaliacaoEstabelecimento obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		/*
		 * 
		 * [GET]: Exibe somente a avaliação de estabelecimento passada por {id}, com: IDAVALIACAO, DATACRIACAO, DESCRICAO, CLASSIFICACAO, USUARIO_NICKNAME
		 * Endpoint:  http://{host-url}/api/avaliacoes/{id}
		 * 
		 * @PreAuthorize("hasAnyRole('ADMIN')") ////Autorização de endpoint para perfil especifico - ADMIN: apenas usuário admin poderá ter acesso ao endpoint
		 * 
		 * */
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/avaliacaoestabelecimento/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		AvaliacaoEstabelecimentoDTO aeDto = service.findById(id);
		return ResponseEntity.ok().body(aeDto);
		/*
		 * 	[GET] http://localhost:8080/api/avaliacoes/avaliacaoestabelecimento/1
		 * 
		 * */
	}
	
	@RequestMapping(value = "/estabelecimento", method = RequestMethod.GET)
	public ResponseEntity<Page<AvaliacaoEstabelecimentoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dataCriacao") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		Page<AvaliacaoEstabelecimento> list = service.search(nomeDecoded, page, linesPerPage, orderBy, direction);
		Page<AvaliacaoEstabelecimentoDTO> listDto = list.map(obj -> new AvaliacaoEstabelecimentoDTO(obj));
		return ResponseEntity.ok().body(listDto);
		
		/*
		 * [GET] endpoint http://{host-url}/api/avaliacoes/estabelecimento   // exibe as avaliações de estabelecimento da página 0
		 * [GET] endpoint http://{host-url}/api/avaliacoes/estabelecimento/?nome={String}   // pesquisa por nome do estabelecimento
		 * [GET] endpoint http://{host-url}/api/avaliacoes/estabelecimento/?page={page}   // número da página (padrão é 0)
		 * [GET] endpoint http://localhost:8080/api/avaliacoes/estabelecimento/?nome={String}&page={page}
		 */
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AvaliacaoEstabelecimento>> findAll() {
		List<AvaliacaoEstabelecimento> list = service.findAll();
		return ResponseEntity.ok().body(list);
		/*
		 * 
		 * [GET]: Exibe lista de avaliações de estabelecimento com: IDAVALIACAO, ESTABELECIMENTO_ID, ESTABELECIMENTO_NOME, DATACRIACAO, DESCRICAO, CLASSIFICACAO, USUARIO_ID, USUARIO_APELIDO
		 * Endpoint:  http://{host-url}/api/avaliacoes/
		 * 
		 * @PreAuthorize("hasAnyRole('ADMIN')") ////Autorização de endpoint para perfil especifico - ADMIN: apenas usuário admin poderá ter acesso ao endpoint
		 * 
		 * */
	}
	@ApiOperation(value = "POST Avaliação", nickname = "Cria avaliação de um estabelecimento")
	@PreAuthorize("hasAnyRole('STANDARD')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)	
	public ResponseEntity<Void> insert(@RequestBody AvaliacaoEstabelecimentoNewDTO objDto){
		AvaliacaoEstabelecimento obj = service.fromAEDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdAvaliacao()).toUri();
		return ResponseEntity.created(uri).build();
		/*
		 *
		 * [POST]: cria uma avaliação de um estabelecimento
		 * Endpoint:  http://{host-url}/api/avaliacoes/new
		 * 
		 * Formato do JSON, exemplo:
		 * 
		 	{
    			"descricao": "TESTE JSON POST inserindo uma nova avaliação no estabelecimento ",
    			"classificacao": "4.1",
    			"usuarioEmail":"test5test@test.com",
    			"estabelecimentoId": "12"
			}
			 
		 * OBS. Para criar uma avaliação é preciso inserir no Headers Authorization o token recebido na autenticação. Na criação da avaliação é necessário o usuario passar seu email de login.
		 * 
		 * @PreAuthorize("hasAnyRole('STANDARD')") ////Autorização de endpoint para perfil especifico
		 * 
		 * */
	}	
}