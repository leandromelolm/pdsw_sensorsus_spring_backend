package br.com.sensorsus.sensorsus.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
import br.com.sensorsus.sensorsus.security.UserSS;
import br.com.sensorsus.sensorsus.services.exceptions.AuthorizationException;
import br.com.sensorsus.sensorsus.services.exceptions.DatabaseException;
import br.com.sensorsus.sensorsus.services.exceptions.ObjectNotFoundException;
import br.com.sensorsus.sensorsus.services.exceptions.ResourceNotFoundException;

@Service
public class AvaliacaoEstabelecimentoService {
	
	@Autowired
	private AvaliacaoEstabelecimentoRepository repo;

	@Autowired
	private EstabelecimentoRepository repoEstab;

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
	
	
	public Page<AvaliacaoEstabelecimento> searchIdUsuario(Integer usuarioId, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
//		return repo.searchIdEstabelecimento(estabelecimentoId, pageRequest);
		return repo.findByUsuarioId(usuarioId, pageRequest);
	}
	

	@Transactional
	public AvaliacaoEstabelecimentoNewDTO insert(AvaliacaoEstabelecimentoNewDTO dto) {
		// Método insert cria uma nova avaliação, e não permite repetir uma avaliação no mesmo estabelecimento
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !dto.getUsuarioEmail().equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		AvaliacaoEstabelecimento entity = new AvaliacaoEstabelecimento();		
		entity.setIdAvaliacao(null);
		entity.setDataCriacao(new Date());	
		
		copyDtoToEntity(dto, entity);
		
		Estabelecimento objEstab = repoEstab.findById(dto.getEstabelecimentoId()).get();
		for (AvaliacaoEstabelecimento avaest : objEstab.getAvaliacoes()) {

			if(user.getUsername().equals(avaest.getUsuario().getEmail())) {				
							
				throw new AuthorizationException("Avaliação já realizada para esta unidade de saúde");
			}
		}
		
		double sum = 0.0;
		for (AvaliacaoEstabelecimento ae : objEstab.getScores()) {
			sum = sum + ae.getClassificacao();			
		}
		sum = sum + entity.getClassificacao();
		
		Integer count = objEstab.getScores().size()+1;

		double avg = sum / count;
		
		objEstab.setScore(avg);
		objEstab.setCount(count);		
		objEstab = repoEstab.save(objEstab);	
		
		
		entity = repo.save(entity);
		return new AvaliacaoEstabelecimentoNewDTO(entity);
	}	
	
	@Transactional
	public AvaliacaoEstabelecimento update(AvaliacaoEstabelecimentoNewDTO dto) {		
		// Método update cria nova avaliação se não houve, caso contrário altera a antiga avaliação	
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !dto.getUsuarioEmail().equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		AvaliacaoEstabelecimento entity = new AvaliacaoEstabelecimento();
		
		copyDtoToEntity(dto, entity);
		
		Estabelecimento objEstab = repoEstab.findById(dto.getEstabelecimentoId()).get();
		
		for (AvaliacaoEstabelecimento avaest : objEstab.getAvaliacoes()) {
			
			// O IF verifica se já existe uma avaliação do usuario associada ao estabelecimento
			// se SIM: seta o id da avaliação e altera com dados passados.
			if(user.getUsername().equals(avaest.getUsuario().getEmail())) { 
				
//				System.out.println("idAvaliação = " + avaest.getIdAvaliacao());
				entity.setIdAvaliacao(avaest.getIdAvaliacao()); //Seta ID da antiga avaliação
				entity.setClassificacao(dto.getClassificacao());
				entity.setDescricao(dto.getDescricao());
				entity.setDataCriacao(new Date());
				
				objEstab = repoEstab.save(objEstab);
				
				double sum = 0.0;
				for (AvaliacaoEstabelecimento ae : objEstab.getScores()) {
					sum = sum + ae.getClassificacao();	
				}
				
				// subtraindo nota anterior feita pelo usuario, para atualizar com nova nota em seguida e recalcular a média
				sum = sum - avaest.getClassificacao(); 
				
				sum = sum + entity.getClassificacao();
				
				Integer count = objEstab.getScores().size();

				double avg = sum / count;		
				
				objEstab.setScore(avg);
				objEstab.setCount(count);		
				objEstab = repoEstab.save(objEstab);				
				return repo.save(entity);	
			}
		}
		
		//Cria nova avaliação se não houve uma avaliação associando o usuario ao estabelecimento		
		double sum = 0.0;
		for (AvaliacaoEstabelecimento ae : objEstab.getScores()) {
			sum = sum + ae.getClassificacao();			
		}
		sum = sum + entity.getClassificacao();
		
		Integer count = objEstab.getScores().size()+1;

		double avg = sum / count;
		
		objEstab.setScore(avg);
		objEstab.setCount(count);		
		objEstab = repoEstab.save(objEstab);

		return repo.save(entity);
	}
	
	
	public void delete(Integer id) {
		
		UserSS user = UserService.authenticated();	
		
		Optional<AvaliacaoEstabelecimento> avaliestab = repo.findById(id); // findbyId para encontrar na tabela, buscando por meio do id, a avaliação com o valor do id. Em seguida é salvo no objeto avaliestab		
		avaliestab.orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não existe!")); // caso não exista lança uma exceção		
		
		if (!user.getId().equals(avaliestab.get().getUsuario().getId())) { // verificação se id do usuario logado é igual ao id da avaliação recebida no parametro do método
			throw new AuthorizationException("Não é permitido remover avaliação de outro usuário");
		}
		
		Optional<Estabelecimento> estabelecimentoOpt = repoEstab.findById(avaliestab.get().getEstabelecimento().getId());
		Estabelecimento estabelecimento = estabelecimentoOpt.get();
		
		double sum = 0.0;
		for (AvaliacaoEstabelecimento ae : estabelecimentoOpt.get().getScores()){			
			sum = sum + ae.getClassificacao();			
		}
		sum = sum - avaliestab.get().getClassificacao(); // subtração da nota que será removida		
		Integer quant = estabelecimentoOpt.get().getScores().size()-1; //subtração de uma (1) avaliação da quantidade total
		
		if(quant < 1) { // se quantidade de notas for menor que 1 já é atribuido os valores 0 para média e para quantidade de avaliações 
			estabelecimento.setScore(0.0);
			estabelecimento.setCount(0);		
			estabelecimento = repoEstab.save(estabelecimento);
		}else {			
			double avg = sum / quant; // calcula nova média
			estabelecimento.setScore(avg);
			estabelecimento.setCount(quant);		
			estabelecimento = repoEstab.save(estabelecimento);			
		}		
		
		try {
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não existe " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade no banco");
		}
	}
	
	private void copyDtoToEntity(AvaliacaoEstabelecimentoNewDTO dto, AvaliacaoEstabelecimento entity ) {		
		UserSS user = UserService.authenticated();		
		Usuario usuario = new Usuario();
		usuario.setId(user.getId());
		usuario.setEmail(dto.getUsuarioEmail());		
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setId(dto.getEstabelecimentoId());
		
		entity.setDataCriacao(new Date());
		entity.setDescricao(dto.getDescricao());
		entity.setClassificacao(dto.getClassificacao());
		entity.setUsuario(usuario);
		entity.setEstabelecimento(estabelecimento);		
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
	
}







/*
 * 
 * // Método passando Email do Usuário
 * public AvaliacaoEstabelecimento fromAEDTO(AvaliacaoEstabelecimentoNewDTO objDto)
 * Formato do JSON, exemplo:

	{
    	"descricao": "TESTE JSON POST inserindo uma nova avaliação no estabelecimento ",
    	"classificacao": "4.1",
    	"usuarioEmail":"aaa@aaa.com",
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