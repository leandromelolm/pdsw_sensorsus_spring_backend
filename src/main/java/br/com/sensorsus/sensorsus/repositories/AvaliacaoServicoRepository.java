package br.com.sensorsus.sensorsus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sensorsus.sensorsus.model.AvaliacaoServico;

@Repository
public interface AvaliacaoServicoRepository extends JpaRepository<AvaliacaoServico, Integer> {
	
}
