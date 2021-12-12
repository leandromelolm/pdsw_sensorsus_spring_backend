package br.com.sensorsus.sensorsus.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sensorsus.sensorsus.dto.AvaliacaoServicoDTO;
import br.com.sensorsus.sensorsus.dto.ServicoAvaliacaoDTO;
import br.com.sensorsus.sensorsus.dto.ServicoDTO;
import br.com.sensorsus.sensorsus.model.Servico;
import br.com.sensorsus.sensorsus.services.ServicoService;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoController {

	@Autowired
	private ServicoService service;
	
	/* lista um serviço cadastrado e sua avaliações feitas pelos usuários*/
	/* endpoint /servicos/{id} */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		ServicoAvaliacaoDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	// **listando todos estabelecimentos e suas respectivas avaliacões com DTO -
	// EndPoint '/estabelecimento/avaliacoes'*/
	@RequestMapping(value = "/avaliacoes", method = RequestMethod.GET)
	public ResponseEntity<List<AvaliacaoServicoDTO>> findAvaliacao() {
		List<Servico> list = service.findAll();
		List<AvaliacaoServicoDTO> listAvalicaoDto = list.stream().map(obj -> new AvaliacaoServicoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listAvalicaoDto);
	}
	
	/* lista todos os serviços cadastrados e o estabelecimento que presta o serviço*/
	/* endpoint /servicos */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ServicoDTO>> findAll() {
		List<Servico> list = service.findAll();
		List<ServicoDTO> listDto = list.stream().map(obj -> new ServicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
