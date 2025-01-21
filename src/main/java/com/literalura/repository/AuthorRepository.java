package com.literalura.repository;

import com.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books ORDER BY a.name")
    List<Author> findAllAuthors();

    @Query("SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books " +
           "WHERE a.anoNascimento <= :ano " +
           "AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano) " +
           "ORDER BY a.name")
    List<Author> findAutoresVivosPorAno(@Param("ano") Integer ano);
}
