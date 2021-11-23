package br.com.sensorsus.sensorsus;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sensorsus.sensorsus.model.Usuario;
import br.com.sensorsus.sensorsus.repositories.UsuarioRepository;

@SpringBootApplication
public class SensorsusApplication implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository usuarioReposity;
	
	public static void main(String[] args) {
		SpringApplication.run(SensorsusApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Usuario user1 = new Usuario(null,"usuario1 test1", "userTest2", "1234" , "test1@test.com");
		Usuario user2 = new Usuario(null,"usuario2 test2", "userTest2", "1234222" , "test2@test.com");
		Usuario user3 = new Usuario(null,"user3 test2", "userTest2", "1234" , "test33333@test.com");
		
		usuarioReposity.saveAll(Arrays.asList(user1,user2,user3));
	}

}
