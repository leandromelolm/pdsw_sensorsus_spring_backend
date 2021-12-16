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
		 * Método GET: Usuario por ID. Exibe somente NICKNAME
		 * Endpoint: /usuarios/nickname/{id}
		 * 
		 * */
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		UsuarioDTO usuario = usuarioservice.buscar(id);
		return ResponseEntity.ok().body(usuario);
		/*
		 * 
		 * Método GET: Usuario por ID. Exibe ID, NOME, NICKNAME, EMAIL
		 * Endpoint: /usuarios/{id}
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
		 * Método GET: exibe lista de todos Usuarios com ID, NOME, NICKNAME, EMAIL
		 * Endpoint: /usuarios
		 * 
		 * @PreAuthorize("hasAnyRole('ADMIN')") //Autorização de endpoint para perfil especifico - Perfil ADMIN: apenas usuário ADMIN poderá ter acesso ao endpoint
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
		 * Método GET: exibe Lista de todos Usuarios com somente o NICKNAME (APELIDO)
		 * Endpoint: /usuarios/nickname
		 * 
		 * @PreAuthorize("hasAnyRole('ADMIN')") //Autorização de endpoint para perfil especifico
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
		/*
		 * 
		 * Método POST: cria um novo usuário
		 * Endpoint: /usuarios/new
		 * Formato do JSON exemplo:
		 * 
		 	{    
    			"nomeCompleto": "Michelangelo di Lodovico Buonarroti Simoni",
    			"nickname": "Michelangelo",    
    			"email": "Michelangelo_sensorsus@gmail.com",
    			"senha": "123456"   
			}
		 * 
		 * */	
	}	
}