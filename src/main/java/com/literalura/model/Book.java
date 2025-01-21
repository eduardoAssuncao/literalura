package com.literalura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_languages")
    private Set<String> idiomas = new HashSet<>();

    private String numeroDownloads;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Author> authors = new HashSet<>();

    public Book(){}

    public Book(DadosBook dadosBook){
        this.title = dadosBook.title();
        this.idiomas = dadosBook.languages() != null ? new HashSet<>(dadosBook.languages()) : new HashSet<>();
        this.numeroDownloads = dadosBook.numeroDownloads();
        this.authors = dadosBook.authors() != null ? 
            dadosBook.authors().stream()
                .map(authorData -> new Author(
                    authorData.name(),
                    authorData.anoNascimento(),
                    authorData.anoFalecimento()))
                .collect(Collectors.toSet())
            : new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", idiomas=" + idiomas +
                ", numeroDownloads='" + numeroDownloads + '\'' +
                ", authors=" + authors +
                '}';
    }
}
