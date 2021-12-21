package br.com.sensorsus.sensorsus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SensorSus API", version = "1.0", description = "Aplicação para avaliação de estabelecimentos de saúde"))
public class SensorsusApplication implements CommandLineRunner {	
	
	public static void main(String[] args) {
		SpringApplication.run(SensorsusApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
	}
}







/*
 * 
 
Principais endpoints  - Formato Json para teste 

[GET] LISTA DE AVALIAÇÕES DE ESTABELECIMENTO POR DATA MAIS RECENTE (COM PAGINAÇÃO)
http://localhost:8080/api/avaliacoes/estabelecimento

[GET] PESQUISA POR NOME DE ESTABELECIMENTO. AS AVALIAÇÕES SÃO EXIBIDAS POR DATA MAIS RECENTE (COM PAGINAÇÃO)
http://localhost:8080/api/avaliacoes/estabelecimento/?nome={String}

[GET] SELECIONA PÁGINA DA LISTA DE AVALIAÇÕES DE ESTABELECIMENTO EXIBINDO POR DATA MAIS RECENTE (COM PAGINAÇÃO)
http://localhost:8080/api/avaliacoes/estabelecimento/?page={page}

[GET] FILTRO EM CONJUNTO - NOME DO ESTABELECIMENTO E PÁGINA
localhost:8080/api/avaliacoes/estabelecimento/?nome={String}&page={page}


[POST] CADASTRO DE USUÁRIO
http://localhost:8080/usuarios/new
{    
    "nomeCompleto": "Michelangelo di Lodovico Buonarroti Simoni",
    "nickname": "Michelangelo",    
    "email": "michelangelo_sensorsus@gmail.com",
    "senha": "123456"   
}

[POST] LOGIN DE USUÁRIO
http://localhost:8080/login
{
    "email": "michelangelo_sensorsus@gmail.com",
    "senha": "123456"   
}

[POST] CRIAR UMA AVALIAÇÃO DE ESTABELECIMENTO (APENAS PARA O USUÁRIO LOGADO (COM TOKEN VÁLIDO))
http://localhost:8080/api/avaliacoes/new
{
    "descricao": "TESTE JSON POST inserindo uma nova avaliação no estabelecimento ",
    "classificacao": "4.1",
    "usuarioEmail":"michelangelo_sensorsus@gmail.com",
    "estabelecimentoId": "2"
}

[GET] PERFIL USUÁRIO CADASTRO ( O USUÁRIO LOGADO (TOKEN VÁLIDO) RECUPERA SUAS PRÓPRIAS INFORMAÇÕES COM EMAIL)
http://localhost:8080/usuarios/email?value=michelangelo_sensorsus@gmail.com"


SWAGGER
// http://localhost:8080/swagger-ui/index.html?configUrl=/sensorsus-openapi/swagger-config#/

// http://localhost:8080/swagger-ui/index.html
// campo explorer: /sensorsus-openapi


 * 
 * */
