package br.com.sensorsus.sensorsus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sensorsus.sensorsus.dto.AvaliacaoServicoTesteDTO;
import br.com.sensorsus.sensorsus.services.AvaliacaoServicoTesteService;

@RestController
@RequestMapping(value = "/avaliacoesservico")
public class AvaliacaoServicoTesteController {

	@Autowired
	private AvaliacaoServicoTesteService service;
	
	/* Apresenta uma avaliação em específico */
	@GetMapping(value = "/{id}")
	public AvaliacaoServicoTesteDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	/* Apresenta todas as avaliacões de serviços em ordem de ID com nome a informação da avaliação, o estabelecimento e o username de quem fez a avaliação */
	/* /avaliacoesservico */
	@GetMapping
	public ResponseEntity<List<AvaliacaoServicoTesteDTO>> findAll(){
		List<AvaliacaoServicoTesteDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
}



/*
 * 
 *   {
    "idAvaliacao": 8,
    "nomeServico": "Emergência adulta",
    "nomeEstabelecimento": "Hospital Alfa",
    "dataCriacao": "2021-09-29T13:31:00.000+00:00",
    "descricao": "serviçoatendimento usuario 1 avaliando",
    "classificacao": 4.5,
    "nomeUsuario": "userTest1"
  },
 * 
 * 
 * 
 * 
 * */
