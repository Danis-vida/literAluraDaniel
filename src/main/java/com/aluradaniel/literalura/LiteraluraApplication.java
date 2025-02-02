package com.aluradaniel.literalura;

import com.aluradaniel.literalura.principal.Principal;
import com.aluradaniel.literalura.repository.AutorRepository;
import com.aluradaniel.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private AutorRepository autorRepository;
	@Autowired
    private LibroRepository libroRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(autorRepository, libroRepository);
        principal.inicio();
    }
}
