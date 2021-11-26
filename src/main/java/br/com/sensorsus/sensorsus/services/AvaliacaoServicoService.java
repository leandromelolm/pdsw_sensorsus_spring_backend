package br.com.sensorsus.sensorsus.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sensorsus.sensorsus.model.AvaliacaoServico;
import br.com.sensorsus.sensorsus.repositories.AvaliacaoServicoRepository;
import br.com.sensorsus.sensorsus.services.exceptions.ObjectNotFoundException;

@Service
public class AvaliacaoServicoService {
	
	@Autowired
	private AvaliacaoServicoRepository repo;
	
	public AvaliacaoServico find(Integer id) {
		Optional<AvaliacaoServico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + AvaliacaoServico.class.getName()));
	}

}
