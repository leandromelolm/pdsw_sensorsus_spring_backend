package br.com.sensorsus.sensorsus;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;
import br.com.sensorsus.sensorsus.model.Estabelecimento;
import br.com.sensorsus.sensorsus.model.Usuario;
import br.com.sensorsus.sensorsus.model.enums.TipoUsuario;
import br.com.sensorsus.sensorsus.repositories.AvaliacaoEstabelecimentoRepository;
import br.com.sensorsus.sensorsus.repositories.EstabelecimentoRepository;
import br.com.sensorsus.sensorsus.repositories.UsuarioRepository;

@SpringBootApplication
public class SensorsusApplication implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository usuarioReposity;
	@Autowired
	private AvaliacaoEstabelecimentoRepository avaliacaoEstabelecimentoReposity;
	@Autowired
	private EstabelecimentoRepository estabelecimentoReposity;
	
	public static void main(String[] args) {
		SpringApplication.run(SensorsusApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Usuario user1 = new Usuario(null,"usuario1 test1", "userTest2", "55555" , "test1@test.com", TipoUsuario.ADMIN);
		Usuario user2 = new Usuario(null,"usuario2 test2", "userTest2", "22222" , "test2@test.com",TipoUsuario.DEFAULT);
//		Usuario user3 = new Usuario(null,"user3 test2", "userTest2", "1234" , "test33333@test.com",TipoUsuario.DEFAULT);
		
		Estabelecimento e1 = new Estabelecimento(null, "Hospital B", 23231, " Recife", "Municipal", "Administracão pública");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		AvaliacaoEstabelecimento avaliacaoE1 = new AvaliacaoEstabelecimento(null, sdf.parse("30/09/2017 10:32"),"Muito Bom",4.0, user2, e1);
		
		usuarioReposity.saveAll(Arrays.asList(user1,user2));
		estabelecimentoReposity.saveAll(Arrays.asList(e1));
		avaliacaoEstabelecimentoReposity.saveAll(Arrays.asList(avaliacaoE1));
	}

}
