package br.com.sensorsus.sensorsus.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sensorsus.sensorsus.dto.AvaliacaoEstabelecimentoDTO;
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
	
	public AvaliacaoEstabelecimentoDTO findById(Integer id) {
		AvaliacaoEstabelecimento obj = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + AvaliacaoEstabelecimento.class.getName()));
		return toAvalEstabDTO(obj);
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
		/*
		 * 
		 * Formato do JSON, exemplo:
		 * 
		   {   
    			"descricao": "TESTE JSON POST nova avaliação",
    			"classificacao": "4.1",
    			"usuarioId": "5",
    			"estabelecimentoId": "11"  
			}
		 * 
		 * OBS. Para criar uma avaliação é preciso inserir no Headers Authorization o token recebido na autenticação. Na criação da avaliação é necessário o usuario insira seu id.
		 * 
		 * */
	
	
	}
	
	public AvaliacaoEstabelecimento fromAEDTO(AvaliacaoEstabelecimentoNewDTO objDto) {
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !objDto.getUsuarioEmail().equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		Usuario usuario = new Usuario(user.getId(), null, null,objDto.getUsuarioEmail(), null);
		Estabelecimento estab = new Estabelecimento(objDto.getEstabelecimentoId(), null, null, null, null, null);
		AvaliacaoEstabelecimento avalEstab = new AvaliacaoEstabelecimento(null, objDto.getDataCriacao(), objDto.getDescricao(), objDto.getClassificacao(), usuario, estab);
		return avalEstab;
		/*
		 * Formato do JSON, exemplo:
		 * 		
		 	{
    			"descricao": "TESTE JSON POST inserindo uma nova avaliação no estabelecimento ",
    			"classificacao": "4.1",
    			"usuarioEmail":"test5test@test.com",
    			"estabelecimentoId": "12"
			}
		 *  
		 *  OBS. Para criar uma avaliação é preciso inserir no Headers Authorization o token recebido na autenticação. Na criação da avaliação é necessário o usuario passar seu email de login.
		 *  
		 * */
	}
	
	private AvaliacaoEstabelecimentoDTO toAvalEstabDTO (AvaliacaoEstabelecimento ae) {
		var aeDto = new AvaliacaoEstabelecimentoDTO();
		aeDto.setIdAvaliacao(ae.getIdAvaliacao());
		aeDto.setClassificacao(ae.getClassificacao());
		aeDto.setDescricao(ae.getDescricao());
		aeDto.setDataCriacao(ae.getDataCriacao());
		aeDto.setEstabelecimentoId(ae.getEstabelecimento().getId());
		aeDto.setNomeEstabelecimento(ae.getEstabelecimento().getNome());
		aeDto.setUsuarioId(ae.getUsuario().getId());
		aeDto.setApelido(ae.getUsuario().getNickname());
		return aeDto;
	} 
}