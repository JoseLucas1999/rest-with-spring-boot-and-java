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

/*
comentar um bloco de texto : Shift + Ctrl + /

copiar linha: ctrl + alt + down arrow

mover linha: alt + setas dowm/up

selecionar linha: home/end + side arrow

reanomear todos: alt + shift + R

Apagar linha: Ctrl + D

Surround with (if/try/catch) : Ctrl + Alt + Z

Executar como Java Application: Ctrl + F11

Salvar tudo: Ctrl + Shift + S

ir no fim da chave : Ctrl + Shift + p

criar linha embaixo sem quebra: shift + enter

criar linha em cima sem quebra: ctrl + shift + enter


*/
