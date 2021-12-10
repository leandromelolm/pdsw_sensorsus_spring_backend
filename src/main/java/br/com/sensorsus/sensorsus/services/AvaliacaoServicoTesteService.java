package br.com.sensorsus.sensorsus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sensorsus.sensorsus.dto.AvaliacaoServicoTesteDTO;
import br.com.sensorsus.sensorsus.model.AvaliacaoServico;
import br.com.sensorsus.sensorsus.repositories.AvaliacaoServicoRepository;

@Service
public class AvaliacaoServicoTesteService {
	
	@Autowired
	private AvaliacaoServicoRepository repository;
	
	@Transactional(readOnly = true)
	public AvaliacaoServicoTesteDTO findById(Integer id) {
		AvaliacaoServico entity = repository.findById(id).get();
		AvaliacaoServicoTesteDTO dto = new AvaliacaoServicoTesteDTO(entity);
		return dto;
	}

	public List<AvaliacaoServicoTesteDTO> findAll() {
		List<AvaliacaoServico> result = repository.findAll();
		return result.stream().map(x -> new AvaliacaoServicoTesteDTO(x)).collect(Collectors.toList());
	}

}
