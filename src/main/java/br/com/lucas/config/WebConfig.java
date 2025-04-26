package br.com.lucas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

//	via QUERY
//	key: mediaType (ignore if accept is on)
//	value:  application/xml
//	URL: ?mediaType=xml
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.
//		favorParameter(false) // habilita uso de parâmetro
//		.ignoreAcceptHeader(false) // ignora o Accept do header
//		.useRegisteredExtensionsOnly(false)
//		.defaultContentType(MediaType.APPLICATION_JSON)
//		.mediaType("json", MediaType.APPLICATION_JSON)
//		.mediaType("xml", MediaType.APPLICATION_XML);
//	}

	
//	via HEADER
//	value:  application/json
//	key: accept
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.
		favorParameter(false) // habilita uso de parâmetro
		.ignoreAcceptHeader(false) // ignora o Accept do header
		.useRegisteredExtensionsOnly(false)
        .defaultContentType(MediaType.APPLICATION_JSON)
        .mediaType("json", MediaType.APPLICATION_JSON)
        .mediaType("xml", MediaType.APPLICATION_XML)
        .mediaType("yaml", MediaType.APPLICATION_YAML);
	}
}

//via QUERY PARAM 

//JSON (padrão):
//http://localhost:8080/api/person/v1/2
	
//XML
//http://localhost:8080/api/person/v1/2?mediaType=xml

//btn dir / source / implement methods