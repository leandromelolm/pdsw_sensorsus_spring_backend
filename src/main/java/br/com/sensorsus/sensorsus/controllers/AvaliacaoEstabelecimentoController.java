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
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		AvaliacaoEstabelecimento obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		/*
		 * 
		 * Método GET: Exibe somente a avaliação de estabelecimento passada por {id}, com: IDAVALIACAO, DATACRIACAO, DESCRICAO, CLASSIFICACAO, USUARIO_NICKNAME
		 * Endpoint: /avaliacoesestabelecimentos/{id}
		 * 
		 * @PreAuthorize("hasAnyRole('ADMIN')") ////Autorização de endpoint para perfil especifico - ADMIN: apenas usuário admin poderá ter acesso ao endpoint
		 * 
		 * */
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AvaliacaoEstabelecimento>> findAll() {
		List<AvaliacaoEstabelecimento> list = service.findAll();
		return ResponseEntity.ok().body(list);
		/*
		 * 
		 * Método GET: Exibe lista de avaliações de estabelecimento com: IDAVALIACAO, DATACRIACAO, DESCRICAO, CLASSIFICACAO, USUARIO_NICKNAME
		 * Endpoint: /avaliacoesestabelecimentos
		 * 
		 * @PreAuthorize("hasAnyRole('ADMIN')") ////Autorização de endpoint para perfil especifico - ADMIN: apenas usuário admin poderá ter acesso ao endpoint
		 * 
		 * */
	}
	
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
		 * Método POST: cria uma avaliação de um estabelecimento
		 * Endpoint: /avaliacoesestabelecimentos/new
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