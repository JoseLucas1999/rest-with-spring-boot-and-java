package br.com.lucas.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GenderSerializer extends JsonSerializer<String> {

//	formatar o atributo gender de PersonDTO
	@Override
	public void serialize(String gender, JsonGenerator gen, 
			SerializerProvider serializers) throws IOException {
		String formateGender = "Male".equals(gender) ? "M" : "F";
		gen.writeString(formateGender);
	}

}
