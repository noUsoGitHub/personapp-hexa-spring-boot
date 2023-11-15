package co.edu.javeriana.as.personapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.javeriana.as.personapp.terminal.menu.MenuPrincipal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class PersonAppCli implements CommandLineRunner {
	
	@Autowired
	private MenuPrincipal menuPrincipal;
	private static String  id = "CLI[PERSON]: ";
	public static void main(String[] args) {
		log.info(id+"Starting");
		SpringApplication.run(PersonAppCli.class, args);
		log.info(id+"OK");
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(id+"Ejecutando");
		menuPrincipal.inicio();
		log.info(id+"Finalizado");
	}

}
