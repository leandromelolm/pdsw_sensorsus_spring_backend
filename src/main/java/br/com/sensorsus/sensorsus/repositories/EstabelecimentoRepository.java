package br.com.sensorsus.sensorsus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sensorsus.sensorsus.model.Estabelecimento;


@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer> {
	
}
