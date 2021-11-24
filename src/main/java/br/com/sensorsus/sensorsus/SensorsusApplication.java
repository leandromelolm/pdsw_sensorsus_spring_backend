package br.com.sensorsus.sensorsus;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		
		Usuario user1 = new Usuario(null,"usuario1 test1", "userTest1", "55555" , "test1@test.com", TipoUsuario.ADMIN);
		Usuario user2 = new Usuario(null,"usuario2 test2", "userTest2", "22222" , "test2@test.com",TipoUsuario.DEFAULT);
		Usuario user3 = new Usuario(null,"user3 test3", "userTest3", "2222222" , "test333@test.com",TipoUsuario.DEFAULT);
		Usuario user4 = new Usuario(null,"user4 test4", "userTest4", "123444454" , "test4test@test.com",TipoUsuario.DEFAULT);
		
		Estabelecimento e1 = new Estabelecimento(null, "Hospital Alfa", 33232, " Recife", "Municipal", "Administracão pública");
		Estabelecimento e2 = new Estabelecimento(null, "Hospital Beta", 55555, " Olinda", "Estadual", "Administracão pública");
		Estabelecimento e3 = new Estabelecimento(null, "Hospital Omega", 88888, " Jaboatao", "Municipal", "Filantropico");
		Estabelecimento e4 = new Estabelecimento(null, "Hospital Delta", 599911, " Recife", "Federal", "Administracão pública");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		AvaliacaoEstabelecimento avE1 = new AvaliacaoEstabelecimento(null, sdf.parse("30/09/2021 10:31"),"Muito Bom",4.0, user2, e1);
		AvaliacaoEstabelecimento avE2 = new AvaliacaoEstabelecimento(null, sdf.parse("30/10/2021 10:33"),"Atendimento Ruim",2.2, user1, e4);
		AvaliacaoEstabelecimento avE3 = new AvaliacaoEstabelecimento(null, sdf.parse("25/10/2021 10:35"),"Mais ou menos",2.6, user3, e1);
		AvaliacaoEstabelecimento avE4 = new AvaliacaoEstabelecimento(null, sdf.parse("23/11/2021 11:10"),"Foi Bom o atendimento, mas poderia ser melhor",3.5, user4, e3);
		AvaliacaoEstabelecimento avE5 = new AvaliacaoEstabelecimento(null, sdf.parse("23/11/2021 11:10"),"Atendimento TOP",5.0, user4, e1);
		
		usuarioReposity.saveAll(Arrays.asList(user1,user2,user3,user4));
		estabelecimentoReposity.saveAll(Arrays.asList(e1,e2,e3,e4));
		avaliacaoEstabelecimentoReposity.saveAll(Arrays.asList(avE1,avE2,avE3,avE5,avE4));
	}

}
