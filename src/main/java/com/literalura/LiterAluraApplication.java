package com.literalura;

import com.literalura.main.LiterAluraConsole;
import com.literalura.repository.AuthorRepository;
import com.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepositorio;

    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        LiterAluraConsole principal = new LiterAluraConsole(bookRepositorio, authorRepository);
        principal.exibeMenu();
    }
}
