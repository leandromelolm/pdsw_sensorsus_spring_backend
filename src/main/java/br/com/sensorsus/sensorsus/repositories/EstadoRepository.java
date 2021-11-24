package br.com.sensorsus.sensorsus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sensorsus.sensorsus.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	
}
