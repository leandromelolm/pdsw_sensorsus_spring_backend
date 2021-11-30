package br.com.sensorsus.sensorsus.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sensorsus.sensorsus.model.Estabelecimento;


@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer> {
	
	@Transactional(readOnly=true)
//	@Query("SELECT obj FROM Estabelecimento obj  WHERE obj.nome LIKE %:nome%")
	Page<Estabelecimento> findByNomeContaining( String nome,  Pageable pageRequest);
}
