package br.com.sensorsus.sensorsus.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;

@Repository
public interface AvaliacaoEstabelecimentoRepository extends JpaRepository<AvaliacaoEstabelecimento, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM AvaliacaoEstabelecimento obj WHERE obj.estabelecimento.nome LIKE %:nome%")
//	Page<AvaliacaoEstabelecimento> findByEstabelecimento(@Param("nome")String estabelecimento, Pageable pageRequest);
	Page<AvaliacaoEstabelecimento> search(@Param("nome")String estabelecimento, Pageable pageRequest);
	
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM AvaliacaoEstabelecimento obj WHERE obj.estabelecimento.id LIKE :id")
	Page<AvaliacaoEstabelecimento> searchIdEstabelecimento(@Param("id")Integer estabelecimento, Pageable pageRequest);
}
