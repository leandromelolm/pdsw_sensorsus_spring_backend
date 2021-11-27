package br.com.sensorsus.sensorsus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sensorsus.sensorsus.model.Usuario;
import br.com.sensorsus.sensorsus.repositories.UsuarioRepository;
import br.com.sensorsus.sensorsus.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
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

}
