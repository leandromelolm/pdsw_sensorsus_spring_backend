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
import br.com.sensorsus.sensorsus.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Usuario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	// GET exibe lista com ID, NOME, USERNAME, EMAIL
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<Usuario> list = service.findAll();
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
		Usuario obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
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