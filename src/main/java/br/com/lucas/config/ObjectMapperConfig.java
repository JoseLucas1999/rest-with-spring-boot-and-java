package br.com.lucas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


@Configuration
public class ObjectMapperConfig {
	
//	aqui declaramos um filter para ignorarmos campos que seja necessario
//	sensitiveData éo campo que iremos ignorar
//	PersonFilter é o nome do filtro a ser aplicado
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		
		SimpleFilterProvider filters = new SimpleFilterProvider()
				.addFilter("PersonFilter", 
						SimpleBeanPropertyFilter.serializeAllExcept("sensitiveData"));
		mapper.setFilterProvider(filters);
		return mapper;
	}
}
