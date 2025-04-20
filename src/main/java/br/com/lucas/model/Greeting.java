package br.com.lucas.model;

public record Greeting (long id, String content){
	

}

/*
🧰 Quando usar um record?
Use record quando:

A classe for usada para transporte de dados (DTOs);

Você quer evitar escrever muito boilerplate (código repetitivo);

Precisa de objetos imutáveis e seguros.*/