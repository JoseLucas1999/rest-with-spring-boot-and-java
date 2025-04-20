package br.com.lucas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class startup {

	public static void main(String[] args) {
		SpringApplication.run(startup.class, args);
	}
}

/*
kill task:

netstat -aon | findstr :8080
taskkill /PID 1234 /F

*/