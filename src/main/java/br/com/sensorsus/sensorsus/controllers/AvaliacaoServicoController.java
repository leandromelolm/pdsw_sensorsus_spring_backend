package br.com.sensorsus.sensorsus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sensorsus.sensorsus.dto.AvaliacaoServicoDTO;
import br.com.sensorsus.sensorsus.services.AvaliacaoServicoService;

@RestController
@RequestMapping(value = "/avaliacoesservicos")
public class AvaliacaoServicoController {

	@Autowired
	private AvaliacaoServicoService service;	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "/{id}")
	public AvaliacaoServicoDTO findById(@PathVariable Integer id) {
		return service.findById(id);
		/*
		 * GET: Exibe informações do id da Avaliação de serviço.
		 *  
		 * Endpoint: /avaliacoesservicos/{id}
		 * 
		 * exemplo de JSON recebido
		 * {
    			"idAvaliacao": 1,
    			"nomeServico": "Oncologia",
    			"nomeEstabelecimento": "UPA do alto do morro ",
    			"dataCriacao": "2021-09-30T13:31:00.000+00:00",
    			"descricao": "serviÃ§o Muito Bom",
    			"classificacao": 5.0,
    			"nickname": "Skaggs"
			}
		 * 
		 */
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<AvaliacaoServicoDTO>> findAll(){
		List<AvaliacaoServicoDTO> list = service.findAll();
		return ResponseEntity.ok(list);
		/*
		 * GET: Lista com id da Avaliações de serviço com suas informações
		 *  
		 * Endpoint: /avaliacoesservicos
		 * 
		 * exemplo de JSON recebido
		 * {
        		"idAvaliacao": 1,
        		"nomeServico": "Oncologia",
        		"nomeEstabelecimento": "UPA do alto do morro ",
        		"dataCriacao": "2021-09-30T13:31:00.000+00:00",
        		"descricao": "serviÃ§o Muito Bom",
        		"classificacao": 5.0,
        		"nickname": "Skaggs"
    		}, ....
		 * 
		 */
	}
}