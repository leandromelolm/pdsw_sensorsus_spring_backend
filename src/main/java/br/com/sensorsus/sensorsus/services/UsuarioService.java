package br.com.sensorsus.sensorsus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sensorsus.sensorsus.dto.UsuarioDTO;
import br.com.sensorsus.sensorsus.dto.UsuarioNewDTO;
import br.com.sensorsus.sensorsus.model.Usuario;
import br.com.sensorsus.sensorsus.model.enums.Perfil;
import br.com.sensorsus.sensorsus.repositories.UsuarioRepository;
import br.com.sensorsus.sensorsus.security.UserSS;
import br.com.sensorsus.sensorsus.services.exceptions.AuthorizationException;
import br.com.sensorsus.sensorsus.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario find(Integer id) {
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public UsuarioDTO buscar(Integer id) {
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Usuario usuario = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
		return toUsuarioDTO(usuario);
	}

	public List<Usuario> findAll() {		
		return repo.findAll();
	}
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario user = new Usuario(null, objDto.getNomeCompleto(), objDto.getNickname(), objDto.getEmail(), pe.encode(objDto.getSenha()));
		return user;
	}
	
	private UsuarioDTO toUsuarioDTO(Usuario usuario) {
		var usuarioDto = new UsuarioDTO();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setNickname(usuario.getNickname());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setNomeCompleto(usuario.getNomeCompleto());
		return usuarioDto;
	}
}
