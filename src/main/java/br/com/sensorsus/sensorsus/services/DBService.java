package br.com.sensorsus.sensorsus.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sensorsus.sensorsus.model.AvaliacaoEstabelecimento;
import br.com.sensorsus.sensorsus.model.AvaliacaoServico;
import br.com.sensorsus.sensorsus.model.Cidade;
import br.com.sensorsus.sensorsus.model.Endereco;
import br.com.sensorsus.sensorsus.model.Estabelecimento;
import br.com.sensorsus.sensorsus.model.Estado;
import br.com.sensorsus.sensorsus.model.Servico;
import br.com.sensorsus.sensorsus.model.Usuario;
import br.com.sensorsus.sensorsus.repositories.AvaliacaoEstabelecimentoRepository;
import br.com.sensorsus.sensorsus.repositories.AvaliacaoServicoRepository;
import br.com.sensorsus.sensorsus.repositories.CidadeRepository;
import br.com.sensorsus.sensorsus.repositories.EnderecoRepository;
import br.com.sensorsus.sensorsus.repositories.EstabelecimentoRepository;
import br.com.sensorsus.sensorsus.repositories.EstadoRepository;
import br.com.sensorsus.sensorsus.repositories.ServicoRepository;
import br.com.sensorsus.sensorsus.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;	
	@Autowired
	private UsuarioRepository usuarioReposity;
	@Autowired
	private EstabelecimentoRepository estabelecimentoReposity;	
	@Autowired
	private ServicoRepository servicoReposity;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private AvaliacaoEstabelecimentoRepository avaliacaoEstabelecimentoReposity;
	@Autowired
	private AvaliacaoServicoRepository avaliacaoServicoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Estado est1 = new Estado(null, "PERNAMBUCO");
		Estado est2 = new Estado(null, "PARAIBA");
		
		Cidade c1 = new Cidade(null, "Recife", est1);
		Cidade c2 = new Cidade(null, "João Pessoa", est2);
		Cidade c3 = new Cidade(null, "Campina Grande", est2);
		Cidade c4 = new Cidade(null, "Jaboatão", est1);
		Cidade c5 = new Cidade(null, "Camaragibe", est1);
		Cidade c6 = new Cidade(null, "Paulista", est1);
		Cidade c7 = new Cidade(null, "Olinda", est1);	
		
		est1.getCidades().addAll(Arrays.asList(c1, c4, c5, c6, c7));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));		
				
		Estabelecimento estab1 = new Estabelecimento(null, "Hospital Springfield", 3323, " Recife", "Municipal", "Administracão pública");
		Estabelecimento estab2 = new Estabelecimento(null, "Hospital Wakanda ", 55555, " Olinda", "Estadual", "Administracão pública");		
		Estabelecimento estab3 = new Estabelecimento(null, "HCB Trata Coracao", 8888, " Jaboatao", "Municipal", "Filantropico");
		Estabelecimento estab4 = new Estabelecimento(null, "UPA do alto do morro ", 59911, " Recife", "Federal", "Administracão pública");
		Estabelecimento estab5 = new Estabelecimento(null, "Hospital de Fratura", 54445, " Recife", "Federal", "Administracão pública");
		Estabelecimento estab6 = new Estabelecimento(null, "HGR hospital Geral dos Restaurados", 44443, " Recife", "Federal", "Administracão pública");
		Estabelecimento estab7 = new Estabelecimento(null, "UPA baixa rio lago", 60020, " Recife", "Federal", "Administracão pública");
		Estabelecimento estab8 = new Estabelecimento(null, "UPA Morro baixo", 7777, " Recife", "Federal", "Administracão pública");
		Estabelecimento estab9 = new Estabelecimento(null, "HO hospital do Vida", 66666, " Jaboatao", "Municipal", "Filantropico");
		Estabelecimento estab10 = new Estabelecimento(null, "HCP ", 3333, " Recife", "Federal", "Administracão pública");
		Estabelecimento estab11 = new Estabelecimento(null, "HB2", 35445, " Recife", "Federal", "Administracão pública");
		Estabelecimento estab12 = new Estabelecimento(null, "Hospital de Camaragib", 76444, " Recife", "Federal", "Administracão pública");
		Estabelecimento estab13 = new Estabelecimento(null, "UPA Pedro2", 600, " Recife", "Federal", "Administracão pública");
		Estabelecimento estab14 = new Estabelecimento(null, "Pronto Socorro Villa Grande ", 27554, " Recife", "Federal", "Administracão pública");
		
		Endereco end1 = new Endereco(null, "Avenida Principal", 1000, "Bairro", "Proximo a Rodovia", "70999000", estab1, c4 );		
		Endereco end2 = new Endereco(null, "Rodovia BR 101", 4450, "Cidade Universitaria", "Proximo a UFPE", "70999000", estab2, c1 );		
		Endereco end3 = new Endereco(null, "Avenida Agamenom Magalhaes", 0, "Bairro algum", "Próximo a algum lugar que não sei", "50500500", estab3, c3 );	
		Endereco end4 = new Endereco(null, "Avenida Teste ", 300, "Bairro Velho", "Referencia qualquer", "77999555", estab4, c2 );		
		Endereco end5 = new Endereco(null, "Avenida Test22 ", 201, "Bairro Feliz", "Em algum lugar perto de alguma coisa", "77999555", estab5, c2 );		
		Endereco end6 = new Endereco(null, "Avenida Caxanga ", 506, "Bairro Jovem", "Em algum lugar perto de alguma coisa", "77999555", estab6, c2 );		
		Endereco end7 = new Endereco(null, "Avenida Abidias de Carvalho ", 77, "Sitio Verde", "Em algum lugar perto de alguma coisa", "77999555", estab7, c1 );		
		Endereco end8 = new Endereco(null, "Avenida Boa viagem ", 4088, "Prado", "Em algum lugar perto de alguma coisa", "77999555", estab8, c1 );		
		Endereco end9 = new Endereco(null, "Rua General Carlos Viana", 999, "Madalena", "Em algum lugar perto de alguma coisa", "77999555", estab9, c3 );		
		Endereco end10 = new Endereco(null, "Rua do Principe ", 1221, "Dois Irmão", "Em algum lugar perto de alguma coisa", "77999555", estab10, c1 );
		Endereco end11 = new Endereco(null, "Rua da Concordia ", 232, "Macaxeira", "Em algum lugar perto de alguma coisa", "77999555", estab11, c4 );
		Endereco end12 = new Endereco(null, "Avenida Romero Marques", 0, "Mustarda", "Em algum lugar perto de alguma coisa", "77999555", estab12, c1 );
		Endereco end13 = new Endereco(null, "Av. Porta larga ", 4554, "Recife", "Em algum lugar perto de alguma coisa", "77999555", estab13, c1 );
		Endereco end14 = new Endereco(null, "Avenida Teste ", 488, "Alto do centro", "Em algum lugar perto de alguma coisa", "77999555", estab14, c3 );
				
		estab1.setEndereco(end1);
		estab2.setEndereco(end2);
		estab3.setEndereco(end3);		
		estab4.setEndereco(end4);	
		estab5.setEndereco(end5);
		estab6.setEndereco(end6);
		estab7.setEndereco(end7);
		estab8.setEndereco(end8);
		estab9.setEndereco(end9);
		estab10.setEndereco(end10);
		estab11.setEndereco(end11);
		estab12.setEndereco(end12);
		estab13.setEndereco(end13);
		estab14.setEndereco(end14);
		
		estabelecimentoReposity.saveAll(Arrays.asList(estab1, estab2, estab3, estab4, estab5, estab6, estab7, estab8, estab9, estab10, estab11, estab12, estab13, estab14));
//		estabelecimentoReposity.saveAll(Arrays.asList(estab1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4, end5, end6, end7, end8, end9, end10, end11, end12, end13, end14));
//		enderecoRepository.saveAll(Arrays.asList(end1));
				
		Servico serv1 = new Servico(null, "Emergência adulta", estab1);
		Servico serv2 = new Servico(null, "Hemodiálise", estab1);
		Servico serv3 = new Servico(null, "Emergencia pediátrica", estab2);
		Servico serv4 = new Servico(null, "UTI neonatal", estab2);
		Servico serv5 = new Servico(null, "Emergencia pediátrica", estab3);
		Servico serv6 = new Servico(null, "Odontologia", estab3);
		Servico serv7 = new Servico(null, "Pronto atendimento", estab3);
		Servico serv8 = new Servico(null, "Oncologia", estab4);
		
		estab1.getServicos().addAll(Arrays.asList(serv1,serv2));
		estab2.getServicos().addAll(Arrays.asList(serv3,serv4));
		estab3.getServicos().addAll(Arrays.asList(serv5,serv6, serv7));
		estab4.getServicos().addAll(Arrays.asList(serv8));
		
		servicoReposity.saveAll(Arrays.asList(serv1, serv2, serv3, serv4, serv5, serv6, serv7, serv8));
		
		
		Usuario user1 = new Usuario(null,"Pratik Skaggs", "Skaggs", "test1@test.com", pe.encode("123456"));
		Usuario user2 = new Usuario(null,"Ajani Harding", "Harding", "test2@test.com", pe.encode("222222"));
		Usuario user3 = new Usuario(null,"Lewis Vangon", "Lews", "test333@test.com", pe.encode("333333"));
		Usuario user4 = new Usuario(null,"Salvador Dali", "Dali4", "test4test@test.com", pe.encode("123456"));
		Usuario user5 = new Usuario(null,"Vinaya Justino", "Justino", "test5test@test.com", pe.encode("555555"));
		Usuario user6 = new Usuario(null,"Caetano Muriel", "Muriel", "test6test@test.com", pe.encode("6666666"));
		Usuario user7 = new Usuario(null,"Chipison Johannessen", "Johannessen", "test7test@test.com", pe.encode("7777776"));
		Usuario user8 = new Usuario(null,"Alex Gansa", "Gansa", "test8test@test.com", pe.encode("888888BA"));
		Usuario user9 = new Usuario(null,"Howard Gordon", "Gordon", "test9test@test.com", pe.encode("999WWWWW"));
		
		
		usuarioReposity.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");		
		
		AvaliacaoEstabelecimento avE1 = new AvaliacaoEstabelecimento(null, sdf.parse("30/09/2021 10:31"),"Hospital Muito Bom esse alfa",4.0, user2, estab1);
		AvaliacaoEstabelecimento avE2 = new AvaliacaoEstabelecimento(null, sdf.parse("30/10/2021 10:33"),"Hospital Delta Atendimento Ruim",2.2, user1, estab4);
		AvaliacaoEstabelecimento avE3 = new AvaliacaoEstabelecimento(null, sdf.parse("25/10/2021 10:35"),"Hospital Mais ou menos",2.6, user3, estab1);
		AvaliacaoEstabelecimento avE4 = new AvaliacaoEstabelecimento(null, sdf.parse("23/11/2021 11:10"),"h Beta Foi Bom o atendimento, mas poderia ser melhor",3.5, user4, estab2);
		AvaliacaoEstabelecimento avE5 = new AvaliacaoEstabelecimento(null, sdf.parse("23/11/2021 11:10"),"Hospital alf Atendimento TOP",5.0, user4, estab1);
		AvaliacaoEstabelecimento avE6 = new AvaliacaoEstabelecimento(null, sdf.parse("11/11/2021 11:10"),"Hospital Omega muito bom",5.0, user4, estab3);
		AvaliacaoEstabelecimento avE7 = new AvaliacaoEstabelecimento(null, sdf.parse("11/11/2021 11:10"),"Hospital Omega ja foi melhor 7",5.0, user3, estab3);
		AvaliacaoEstabelecimento avE8 = new AvaliacaoEstabelecimento(null, sdf.parse("11/11/2021 11:10"),"Hospital Omega não sei",5.0, user5, estab3);
		AvaliacaoEstabelecimento avE9 = new AvaliacaoEstabelecimento(null, sdf.parse("11/11/2021 11:10"),"Hospital Omega muito bom 9",5.0, user7, estab3);
		AvaliacaoEstabelecimento avE10 = new AvaliacaoEstabelecimento(null, sdf.parse("11/11/2021 11:10"),"Hospital Beta muito bom10 ",5.0, user6, estab2);
		AvaliacaoEstabelecimento avE11 = new AvaliacaoEstabelecimento(null, sdf.parse("11/11/2021 11:10"),"Hospital Beta muito bom 11",5.0, user8, estab2);
		AvaliacaoEstabelecimento avE12 = new AvaliacaoEstabelecimento(null, sdf.parse("11/11/2021 11:10"),"Hospital Alfa muito bom 12",5.0, user9, estab1);
		AvaliacaoEstabelecimento avE13 = new AvaliacaoEstabelecimento(null, sdf.parse("11/11/2021 11:10"),"Hospital salva todos tem um atendimento rapido",5.0, user4, estab5);
		AvaliacaoEstabelecimento avE14 = new AvaliacaoEstabelecimento(null, sdf.parse("11/11/2021 11:10"),"Hospital salva todos não salva ninguem, atendimento fraco demais",1.0, user1, estab5);
		AvaliacaoEstabelecimento avE15 = new AvaliacaoEstabelecimento(null, sdf.parse("11/11/2021 11:10"),"Hospital salva todos não salva ninguem, atendimento fraco demais",1.0, user1, estab10);
					
		avaliacaoEstabelecimentoReposity.saveAll(Arrays.asList(avE1, avE2, avE3, avE4, avE5, avE6, avE7, avE8, avE9, avE10, avE11, avE12, avE13, avE14, avE15));
		
		
		AvaliacaoServico avServ1 = new AvaliacaoServico(null, sdf.parse("30/09/2021 10:31"),"serviço Muito Bom",5.0, user1, serv8);
		AvaliacaoServico avServ2 = new AvaliacaoServico(null, sdf.parse("30/09/2021 10:31"),"E um serviço Ruim",1.5, user1, serv2);
		AvaliacaoServico avServ3 = new AvaliacaoServico(null, sdf.parse("30/09/2021 10:31"),"Esse serviço Não é muito Legal",3.0, user2, serv5);
		AvaliacaoServico avServ4 = new AvaliacaoServico(null, sdf.parse("30/09/2021 10:31"),"serviço Não sei responder",0.0, user3, serv6);
		AvaliacaoServico avServ5 = new AvaliacaoServico(null, sdf.parse("30/09/2021 10:31"),"serviço Atendimento demorado",3.0, user4, serv7);
		AvaliacaoServico avServ6 = new AvaliacaoServico(null, sdf.parse("28/09/2021 10:31"),"serviço Full HD",4.9, user4, serv7);
		AvaliacaoServico avServ7 = new AvaliacaoServico(null, sdf.parse("29/09/2021 10:31"),"serviçoatendimento ótimo",4.5, user5, serv1);
		AvaliacaoServico avServ8 = new AvaliacaoServico(null, sdf.parse("29/09/2021 10:31"),"serviçoatendimento usuario 1 avaliando",4.5, user1, serv1);
		AvaliacaoServico avServ9 = new AvaliacaoServico(null, sdf.parse("29/09/2021 10:31"),"serviçoatendimento ótimo ótimo ótimo ótimo ótimo",4.5, user4, serv1);
		AvaliacaoServico avServ10 = new AvaliacaoServico(null, sdf.parse("29/09/2021 10:31"),"serviçoatendimento 99999999999999999999999999999999999999999999",4.5, user6, serv1);
		AvaliacaoServico avServ11 = new AvaliacaoServico(null, sdf.parse("29/09/2021 10:31"),"serviçoatendimento wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",4.5, user3, serv1);
		
		avaliacaoServicoRepository.saveAll(Arrays.asList(avServ1, avServ2, avServ3, avServ4, avServ5, avServ6, avServ7, avServ8, avServ9, avServ10, avServ11));
	
	}
}
