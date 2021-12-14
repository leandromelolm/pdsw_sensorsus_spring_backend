package br.com.sensorsus.sensorsus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sensorsus.sensorsus.dto.UsuarioNewDTO;
import br.com.sensorsus.sensorsus.model.Usuario;
import br.com.sensorsus.sensorsus.repositories.UsuarioRepository;
import br.com.sensorsus.sensorsus.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Usuario fromDTO(UsuarioNewDTO objDto) {
//		Usuario user = new Usuario(null, objDto.getNomeCompleto(), objDto.getUsername(), objDto.getSenha(), objDto.getEmail(),TipoUsuario.DEFAULT); // O último parametro 'tipoUsuario' que está sendo passado é o padrão. Para o tipo ser passado no cadastro o parametro deve ser mudando para 'TipoUsuario.toEnum(objDto.getTipoUsuario()'
		Usuario user = new Usuario(null, objDto.getNomeCompleto(), objDto.getUsername(), objDto.getEmail(), pe.encode(objDto.getSenha()));
		return user;
	}

}
