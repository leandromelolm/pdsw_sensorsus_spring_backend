package br.com.sensorsus.sensorsus.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sensorsus.sensorsus.dto.ServicoAvaliacaoDTO;
import br.com.sensorsus.sensorsus.model.Servico;
import br.com.sensorsus.sensorsus.repositories.ServicoRepository;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository repo;

	
//	@Transactional(readOnly = true)
	public ServicoAvaliacaoDTO findById(Integer id) {
		Servico obj = repo.findById(id).get();
		ServicoAvaliacaoDTO dto = new ServicoAvaliacaoDTO(obj);
		return dto;

		
//		Optional<ServicoAvaliacaoDTO> obj = repo2.findById(id);
//		return obj.orElseThrow(() -> new ObjectNotFoundException(
//				"Objeto não encontrado! Id: " + id + ", Tipo: " + Servico.class.getName()));
	}

	public List<Servico> findAll() {
		
		return repo.findAll();
	}


}
