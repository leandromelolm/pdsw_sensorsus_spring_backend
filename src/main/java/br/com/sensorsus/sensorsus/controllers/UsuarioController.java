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
	private UsuarioService usuarioservice;

	@RequestMapping(value = "/nickname/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Usuario obj = usuarioservice.find(id);
		return ResponseEntity.ok().body(obj);
		/*
		 * 
		 * GET Usuario por ID. Exibe somente NICKNAME
		 * /usuarios/nickname/{id}
		 * 
		 * */
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		UsuarioDTO usuario = usuarioservice.buscar(id);
		return ResponseEntity.ok().body(usuario);
		/*
		 * 
		 * GET Usuario por ID. Exibe ID, NOME, NICKNAME, EMAIL
		 * /usuarios/{id}
		 * 
		 * */
	}

	@PreAuthorize("hasAnyRole('ADMIN')")	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<Usuario> list = usuarioservice.findAll();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		/*
		 * 
		 * GET exibe lista de todos Usuarios com ID, NOME, USERNAME, EMAIL
		 * /usuarios
		 * 
		 * */
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/nickname",method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAllNickName() {
		List<Usuario> list = usuarioservice.findAll();		
		return ResponseEntity.ok().body(list);
		/*
		 * 
		 * GET exibe Lista de todos Usuarios com somente o NICKNAME (APELIDO)
		 * /usuarios/nickname
		 * 
		 * */		
	}
	
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UsuarioNewDTO objDto){
		Usuario obj = usuarioservice.fromDTO(objDto);
		obj = usuarioservice.insert(obj);
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