package br.com.sensorsus.sensorsus.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sensorsus.sensorsus.dto.EstabelecimentoAvaliacaoDTO;
import br.com.sensorsus.sensorsus.dto.ServicoAvaliacaoDTO;
import br.com.sensorsus.sensorsus.dto.ServicoDTO;
import br.com.sensorsus.sensorsus.model.Estabelecimento;
import br.com.sensorsus.sensorsus.model.Servico;
import br.com.sensorsus.sensorsus.services.ServicoService;

@RestController
@RequestMapping(value="/servicos")
public class ServicoResource {
	
	@Autowired
	private ServicoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Servico obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//**listando todos estabelecimentos e suas respectivas avaliacões  com DTO - EndPoint '/estabelecimento/avaliacoes'*/
	@RequestMapping(value="/avaliacoes", method=RequestMethod.GET)
	public ResponseEntity<List<ServicoAvaliacaoDTO>> findAvaliacao() {
		List<Servico> list = service.findAll();
		List<ServicoAvaliacaoDTO> listAvalicaoDto = list.stream().map(obj -> new ServicoAvaliacaoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listAvalicaoDto);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ServicoDTO>> findAll() {
		List<Servico> list = service.findAll();		
		List<ServicoDTO> listDto = list.stream().map(obj -> new ServicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
