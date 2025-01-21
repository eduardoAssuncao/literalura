package com.literalura.main;

import com.literalura.model.Book;
import com.literalura.model.Author;
import com.literalura.model.DadosBook;
import com.literalura.model.GutendexResponse;
import com.literalura.repository.AuthorRepository;
import com.literalura.repository.BookRepository;
import com.literalura.service.ConsumoApi;
import com.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class LiterAluraConsole {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "http://gutendex.com/books/";
    private final String API_KEY = "&apikey=6585022c";
    private List<DadosBook> dadosBooks = new ArrayList<>();

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private List<Book> Books = new ArrayList<>();
    private Optional<Book> BookBusca;

    @Autowired
    public LiterAluraConsole(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    1 - Buscar livro por título
                    2 - Listar todos os livros
                    3 - Buscar livros por idioma
                    4 - Listar todos os autores
                    5 - Listar autores vivos em um ano específico
                    6 - Contar livros por idioma
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarBookWeb();
                    break;
                case 2:
                    listarBooksBuscadas();
                    break;
                case 3:
                    buscarLivrosPorIdioma();
                    break;
                case 4:
                    listarAutores();
                    break;
                case 5:
                    listarAutoresVivosPorAno();
                    break;
                case 6:
                    contarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarBookWeb() {
        DadosBook dados = getDadosBook();
        Book book = new Book(dados);
        dadosBooks.add(dados);
        bookRepository.save(book);
        System.out.println(dados);
    }

    private DadosBook getDadosBook() {
        System.out.println("Digite o nome do livro para busca:");
        var nomeBook = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + "?search=" + nomeBook.replace(" ", "+"));
        System.out.println("Json : " + json);
        GutendexResponse response = conversor.obterDados(json, GutendexResponse.class);
        
        if (response.results() == null || response.results().isEmpty()) {
            throw new RuntimeException("Nenhum livro encontrado");
        }
        
        return response.results().get(0);
    }

    private void buscarLivrosPorIdioma() {
        System.out.println("Digite o idioma para busca (ex: pt, en, fr):");
        var idioma = leitura.nextLine().toLowerCase();
        
        var livros = Books.stream()
                .filter(book -> book.getIdiomas() != null && 
                              book.getIdiomas().contains(idioma))
                .toList();
        
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma: " + idioma);
            return;
        }
        
        System.out.println("\nLivros encontrados no idioma " + idioma + ":");
        exibirLivros(livros);
    }

    private void listarBooksBuscadas() {
        Books = bookRepository.findAllBooks();
        if (Books.isEmpty()) {
            System.out.println("Nenhum livro encontrado na base de dados.");
            return;
        }
        
        System.out.println("\nLivros encontrados:");
        exibirLivros(Books);
    }

    private void listarAutores() {
        var autores = authorRepository.findAllAuthors();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado na base de dados.");
            return;
        }

        System.out.println("\nAutores cadastrados:");
        exibirAutores(autores);
    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Digite o ano para buscar autores vivos:");
        var ano = leitura.nextInt();
        leitura.nextLine();

        var autores = authorRepository.findAutoresVivosPorAno(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo no ano " + ano);
            return;
        }

        System.out.println("\nAutores vivos no ano " + ano + ":");
        exibirAutores(autores);
    }

    private void exibirAutores(List<Author> autores) {
        autores.forEach(autor -> {
            System.out.println("\nNome: " + autor.getName());
            if (autor.getAnoNascimento() != null) {
                System.out.println("Ano de Nascimento: " + autor.getAnoNascimento());
            }
            if (autor.getAnoFalecimento() != null) {
                System.out.println("Ano de Falecimento: " + autor.getAnoFalecimento());
            }
            if (!autor.getBooks().isEmpty()) {
                System.out.println("Livros: " + autor.getBooks().stream()
                    .map(Book::getTitle)
                    .collect(Collectors.joining(", ")));
            }
            System.out.println("-".repeat(50));
        });
    }

    private void exibirLivros(List<Book> livros) {
        livros.forEach(book -> {
            System.out.println("\nTítulo: " + book.getTitle());
            System.out.println("Idiomas: " + String.join(", ", book.getIdiomas()));
            System.out.println("Downloads: " + book.getNumeroDownloads());
            if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
                System.out.println("Autores: " + book.getAuthors().stream()
                    .map(Author::getName)
                    .collect(Collectors.joining(", ")));
            }
            System.out.println("-".repeat(50));
        });
    }

    private void contarLivrosPorIdioma() {
        System.out.println("Digite o idioma para contar os livros (ex: pt, en, fr):");
        var idioma = leitura.nextLine().toLowerCase();
        
        var quantidade = bookRepository.countByLanguage(idioma);
        
        if (quantidade == 0) {
            System.out.println("Não há livros cadastrados no idioma: " + idioma);
        } else {
            System.out.println("\nQuantidade de livros no idioma " + idioma + ": " + quantidade);
        }
    }
}
