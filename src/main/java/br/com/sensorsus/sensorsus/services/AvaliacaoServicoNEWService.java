package br.com.sensorsus.sensorsus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sensorsus.sensorsus.dto.AvaliacaoServicoNEWDTO;
import br.com.sensorsus.sensorsus.model.AvaliacaoServico;
import br.com.sensorsus.sensorsus.repositories.AvaliacaoServicoRepository;

@Service
public class AvaliacaoServicoNEWService {
	
	@Autowired
	private AvaliacaoServicoRepository repository;
	
	@Transactional(readOnly = true)
	public AvaliacaoServicoNEWDTO findById(Integer id) {
		AvaliacaoServico entity = repository.findById(id).get();
		AvaliacaoServicoNEWDTO dto = new AvaliacaoServicoNEWDTO(entity);
		return dto;
	}

	public List<AvaliacaoServicoNEWDTO> findAll() {
		List<AvaliacaoServico> result = repository.findAll();
		return result.stream().map(x -> new AvaliacaoServicoNEWDTO(x)).collect(Collectors.toList());
	}

}
