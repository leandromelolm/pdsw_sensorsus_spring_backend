package br.com.sensorsus.sensorsus.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sensorsus.sensorsus.dto.UsuarioDTO;
import br.com.sensorsus.sensorsus.dto.UsuarioNewDTO;
import br.com.sensorsus.sensorsus.model.Usuario;
import br.com.sensorsus.sensorsus.repositories.UsuarioRepository;
import br.com.sensorsus.sensorsus.services.UsuarioService;
import br.com.sensorsus.sensorsus.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioservice;
	
	@Autowired
	private UsuarioRepository repo;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Usuario obj = usuarioservice.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public UsuarioDTO buscar(@PathVariable Integer id) {
		Usuario usuario = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
		return toUsuarioDTO(usuario);
	}

//	@PreAuthorize("hasAnyRole('ADMIN')")
	// GET exibe lista com ID, NOME, USERNAME, EMAIL
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<Usuario> list = usuarioservice.findAll();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	// GET exibe Lista com somente o USERNAME
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<Usuario>> findAll() {
//		List<Usuario> list = service.findAll();		
//		return ResponseEntity.ok().body(list);
//	}
	
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UsuarioNewDTO objDto){
		Usuario obj = usuarioservice.fromDTO(objDto);
		obj = usuarioservice.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
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




/*
 * 
 * JSON USANDO POST /usuarios/new
{    
    "nomeCompleto": "Mike Silva Santos",
    "nickname": "Mike2",    
    "email": "test10test@test.com",
    "senha": "123456"   
}
 * 
 * 
 * */