package br.com.sensorsus.sensorsus.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sensorsus.sensorsus.dto.AvaliacaoEstabelecimentoDTO;
import br.com.sensorsus.sensorsus.dto.AvaliacaoEstabelecimentoNewDTO;
import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;
import br.com.sensorsus.sensorsus.model.Estabelecimento;
import br.com.sensorsus.sensorsus.model.Usuario;
import br.com.sensorsus.sensorsus.model.enums.Perfil;
import br.com.sensorsus.sensorsus.repositories.AvaliacaoEstabelecimentoRepository;
import br.com.sensorsus.sensorsus.repositories.EstabelecimentoRepository;
import br.com.sensorsus.sensorsus.repositories.UsuarioRepository;
import br.com.sensorsus.sensorsus.security.UserSS;
import br.com.sensorsus.sensorsus.services.exceptions.AuthorizationException;
import br.com.sensorsus.sensorsus.services.exceptions.ObjectNotFoundException;

@Service
public class AvaliacaoEstabelecimentoService {
	
	@Autowired
	private AvaliacaoEstabelecimentoRepository repo;

	@Autowired
	private EstabelecimentoRepository repoEstab;
	
	@Autowired
	private UsuarioRepository repoUsuario;

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
//	public AvaliacaoEstabelecimento insert(AvaliacaoEstabelecimento obj) {		
//		obj.setIdAvaliacao(null);
//		obj.setDataCriacao(new Date());		
//		return repo.save(obj);
//	}
	
	// Método passando Email do Usuário
	public AvaliacaoEstabelecimento fromAEDTO(AvaliacaoEstabelecimentoNewDTO objDto) {
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !objDto.getUsuarioEmail().equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Usuario usuario = new Usuario(user.getId(), null, null,objDto.getUsuarioEmail(), null);
		Estabelecimento estab = new Estabelecimento(objDto.getEstabelecimentoId(), null, null, null, null, null);
		AvaliacaoEstabelecimento avalEstab = new AvaliacaoEstabelecimento(null, objDto.getDataCriacao(), objDto.getDescricao(), objDto.getClassificacao(), usuario, estab);				
		
		Estabelecimento objEstab = repoEstab.findById(objDto.getEstabelecimentoId()).get();
		
		for (AvaliacaoEstabelecimento avaestb : objEstab.getAvaliacoes()) {
			System.out.println(avaestb.getUsuario().getNickname());
			System.out.println(avaestb.getUsuario().getEmail());
			System.out.println(avaestb.getIdAvaliacao());
			System.out.println(avaestb.getClassificacao());
			if(user.getUsername().equals(avaestb.getUsuario().getEmail())) {
				
				System.out.println("TEST POST = " + avaestb.getIdAvaliacao());
				avalEstab.setIdAvaliacao(avaestb.getIdAvaliacao());
				avalEstab.setClassificacao(objDto.getClassificacao());
				avalEstab.setDescricao(objDto.getDescricao());
				avalEstab.setDataCriacao(new Date());
				
				objEstab = repoEstab.save(objEstab);
				
				double sum = 0.0;
				for (AvaliacaoEstabelecimento ae : objEstab.getScores()) {
					sum = sum + ae.getClassificacao();			
				}
				sum = sum - avaestb.getClassificacao(); // subtraindo nota anterior feita pelo usuario, para atualizar com nova nota em seguida e recalcular a média
				sum = sum + avalEstab.getClassificacao();
				
				Integer count = objEstab.getScores().size();

				double avg = sum / count;		
				
				// Math.round para arredondar média para duas casas decimais
				double media = Math.round(avg * 100);
				media = media/100;
				
				objEstab.setScore(avg);
				objEstab.setCount(count);		
				objEstab = repoEstab.save(objEstab);
				
				return repo.save(avalEstab);
				
				
				
//				throw new AuthorizationException("Avaliação já realizada para esta unidade de saúde");
			}
		}		
		

		double sum = 0.0;
		for (AvaliacaoEstabelecimento ae : objEstab.getScores()) {
			sum = sum + ae.getClassificacao();			
		}
		sum = sum + avalEstab.getClassificacao();
		
		Integer count = objEstab.getScores().size()+1;

		double avg = sum / count;		
		
		// Math.round para arredondar média para duas casas decimais
		double media = Math.round(avg * 100);
		media = media/100;
		
		objEstab.setScore(media);
		objEstab.setCount(count);		
		objEstab = repoEstab.save(objEstab);

		return avalEstab;
	}

	private AvaliacaoEstabelecimentoDTO toAvalEstabDTO (AvaliacaoEstabelecimento ae) {
		var aeDto = new AvaliacaoEstabelecimentoDTO(ae);
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

	public Page<AvaliacaoEstabelecimento> search(String estabelecimento, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		//		return repo.findByEstabelecimento(estabelecimento, pageRequest);
		return repo.search(estabelecimento, pageRequest);
	}

	public Page<AvaliacaoEstabelecimento> searchIdEstabelecimento(Integer estabelecimentoId, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);		

//		return repo.searchIdEstabelecimento(estabelecimentoId, pageRequest);
		return repo.findByEstabelecimentoId(estabelecimentoId, pageRequest);
	}



	// Método passando ID do Usuario
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
	
	@Transactional
	public AvaliacaoEstabelecimento update(AvaliacaoEstabelecimento obj) {
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !obj.getUsuario().getEmail().equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Usuario usuario = new Usuario(user.getId(), null, null,obj.getUsuario().getEmail(), null);
		//Estabelecimento estab = new Estabelecimento(obj.getEstabelecimento().getId(), null, null, null, null, null);
		Estabelecimento estab = repoEstab.findById(obj.getEstabelecimento().getId()).get();
		AvaliacaoEstabelecimento avalEstab = new AvaliacaoEstabelecimento(obj.getIdAvaliacao(), obj.getDataCriacao(), obj.getDescricao(), obj.getClassificacao(), usuario, estab);	
				
//		find(obj.getIdAvaliacao());
		obj.getEstabelecimento().getId();
		obj.setDataCriacao(new Date());	
		avalEstab.setDataCriacao(new Date());
		return repo.save(avalEstab);
	}

}




/*
 * 
 * // Método passando Email do Usuário
 * public AvaliacaoEstabelecimento fromAEDTO(AvaliacaoEstabelecimentoNewDTO objDto)
 * Formato do JSON, exemplo:

	{
    	"descricao": "TESTE JSON POST inserindo uma nova avaliação no estabelecimento ",
    	"classificacao": "4.1",
    	"usuarioEmail":"test5test@test.com",
    	"estabelecimentoId": "12"
	}
 *
 *  OBS. Para criar uma avaliação é preciso inserir no Headers Authorization o token recebido na autenticação. Na criação da avaliação é necessário o usuario passar seu email de login.
 *  
 */




/* 
 * // Método passando ID do Usuário
 * public AvaliacaoEstabelecimento fromDTO(AvaliacaoEstabelecimentoNewDTO objDto)
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

/*

//public void saveScore(AvaliacaoEstabelecimentoNewDTO dto) {
public void saveScore(AvaliacaoEstabelecimento dto) {
//	Estabelecimento est = repoEstab.findById(dto.getEstabelecimentoId()).get();
	Estabelecimento est = repoEstab.findById(dto.getEstabelecimento().getId()).get();	
	double sum = 0.0;
	for (AvaliacaoEstabelecimento ae : est.getScores()) {
		sum = sum + ae.getClassificacao();		
	}		
	double avg = sum / est.getScores().size();	
	est.setScore(avg);
	est.setCount(est.getScores().size());	
	est = repoEstab.save(est);
}

*/