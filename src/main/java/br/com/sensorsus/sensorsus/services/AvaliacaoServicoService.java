package br.com.sensorsus.sensorsus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sensorsus.sensorsus.dto.AvaliacaoServicoDTO;
import br.com.sensorsus.sensorsus.model.AvaliacaoServico;
import br.com.sensorsus.sensorsus.repositories.AvaliacaoServicoRepository;

@Service
public class AvaliacaoServicoService {
	
	@Autowired
	private AvaliacaoServicoRepository repository;
	
	@Transactional(readOnly = true)
	public AvaliacaoServicoDTO findById(Integer id) {
		AvaliacaoServico entity = repository.findById(id).get();
		AvaliacaoServicoDTO dto = new AvaliacaoServicoDTO(entity);
		return dto;
	}

	public List<AvaliacaoServicoDTO> findAll() {
		List<AvaliacaoServico> result = repository.findAll();
		return result.stream().map(x -> new AvaliacaoServicoDTO(x)).collect(Collectors.toList());
	}

}
