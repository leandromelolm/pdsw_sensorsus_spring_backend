package br.com.sensorsus.sensorsus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.sensorsus.sensorsus.model.Estabelecimento;
import br.com.sensorsus.sensorsus.repositories.EstabelecimentoRepository;
import br.com.sensorsus.sensorsus.services.exceptions.ObjectNotFoundException;

@Service
public class EstabelecimentoService {
	
	@Autowired
	private EstabelecimentoRepository repo;
	
	public Estabelecimento find(Integer id) {
		Optional<Estabelecimento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estabelecimento.class.getName()));
	}

	public List<Estabelecimento> findAll() {
		
		return repo.findAll();
	}
	
	public Page<Estabelecimento> search(String nome, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findByNomeContaining(nome, pageRequest);	
//		return repo.findByNomeLike(nome);	
	}

}
