package br.com.sensorsus.sensorsus.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sensorsus.sensorsus.dto.AvaliacaoEstabelecimentoNewDTO;
import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;
import br.com.sensorsus.sensorsus.model.Estabelecimento;
import br.com.sensorsus.sensorsus.model.Usuario;
import br.com.sensorsus.sensorsus.model.enums.Perfil;
import br.com.sensorsus.sensorsus.repositories.AvaliacaoEstabelecimentoRepository;
import br.com.sensorsus.sensorsus.security.UserSS;
import br.com.sensorsus.sensorsus.services.exceptions.AuthorizationException;
import br.com.sensorsus.sensorsus.services.exceptions.ObjectNotFoundException;

@Service
public class AvaliacaoEstabelecimentoService {
	
	@Autowired
	private AvaliacaoEstabelecimentoRepository repo;
	
	public AvaliacaoEstabelecimento find(Integer id) {
		Optional<AvaliacaoEstabelecimento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + AvaliacaoEstabelecimento.class.getName()));
	}

	public List<AvaliacaoEstabelecimento> findAll() {		
		return repo.findAll();
	}
	
	@Transactional
	public AvaliacaoEstabelecimento insert(AvaliacaoEstabelecimento obj) {		
		obj.setIdAvaliacao(null);
		obj.setDataCriacao(new Date());
		return repo.save(obj);
	}

	public AvaliacaoEstabelecimento fromDTO(AvaliacaoEstabelecimentoNewDTO objDto) {
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !objDto.getUsuarioId().equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Usuario usuario = new Usuario(objDto.getUsuarioId(), null, null, null, null);
		Estabelecimento estab = new Estabelecimento(objDto.getEstabelecimentoId(), null, null, null, null, null);
		AvaliacaoEstabelecimento avalEstab = new AvaliacaoEstabelecimento(null, objDto.getDataCriacao(), objDto.getDescricao(), objDto.getClassificacao(), usuario, estab);
		return avalEstab;
	}

}