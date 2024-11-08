package com.brandon.desafio_tecnico_nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DesafioTecnicoNtApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTecnicoNtApplication.class, args);
	}

}
