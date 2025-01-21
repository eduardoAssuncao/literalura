package com.literalura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer gutenbergId;

    private String title;

    @ElementCollection
    private List<String> subjects;

    @ElementCollection
    private List<String> languages;

    private String downloadCount;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> authors;
}
