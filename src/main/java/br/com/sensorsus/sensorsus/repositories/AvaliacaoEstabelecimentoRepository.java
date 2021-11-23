package br.com.sensorsus.sensorsus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;

@Repository
public interface AvaliacaoEstabelecimentoRepository extends JpaRepository<AvaliacaoEstabelecimento, Integer> {
	
}
