package br.com.lucas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
//DOCUMENTAÇÃO
	
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("REST API's RESTful from 0 with Java, Spring boot, Kubernates and Docker").version("v1")
					.description("REST API's RESTful from 0 with Java, Spring boot, Kubernates and Docker")
					.termsOfService("http://pub.erudio.com.br/meus-cursos")
					.license(new License()
						.name("Apache 2.0")
						.url("http://pub.erudio.com.br/meus-cursos")));
	}

}

/*
 * return new OpenAPI() : Cria uma nova instância de OpenAPI. .info(new Info() :
 * espera um objeto da classe Info, que vai conter informações como título,
 * versão, descrição, etc.
 */
		



/*
 * arquivo OpenAPI em JSON: http://localhost:8080/v3/api-docs
 * usando o Swagger UI (interface gráfica): http://localhost:8080/swagger-ui.html
 * ou : http://localhost:8080/swagger-ui/index.html
 * 
 * Cria um objeto OpenAPI (da biblioteca Swagger/OpenAPI) para configurar a
 * documentação da sua API.
 * 
 * Esse objeto define informações como: Título da API Versão Descrição Termos de
 * serviço Licença
 * 
 * E o @Bean está dizendo para o Spring: "Cria e gerencia esse OpenAPI para mim"
 * 
 * Assim, quando o Swagger for gerar a documentação automática da sua API, ele
 * vai usar essas informações.
 * 
 * A annotation @Bean serve para dizer ao Spring:
 * "Ei, crie e gerencie esse objeto para mim."
 * 
 * 
 * Esse método cria a configuração padrão da documentação da sua API no Swagger,
 * e o @Bean faz o Spring cuidar disso pra injetar onde precisar.
 */
