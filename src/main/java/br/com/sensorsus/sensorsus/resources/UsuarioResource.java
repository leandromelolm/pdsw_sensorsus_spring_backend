package br.com.sensorsus.sensorsus.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sensorsus.sensorsus.model.Usuario;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Usuario> listar() {
		
		Usuario u1 = new Usuario(0, "usuario test", "userTest", "1234" , "test@test.com");
		Usuario u2 = new Usuario(0, "user test2", "userTest2", "1234" , "test2@test.com");
		
		List<Usuario> lista = new ArrayList<>();
		lista.add(u1);
		lista.add(u2);
		
		return lista;
	}

}
