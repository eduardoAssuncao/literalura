package com.literalura.repository;

import com.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT DISTINCT b FROM Book b")
    List<Book> findAllBooks();

    @Query("SELECT DISTINCT b FROM Book b WHERE :language MEMBER OF b.idiomas")
    List<Book> findByLanguage(String language);

    @Query("SELECT COUNT(DISTINCT b) FROM Book b WHERE :language MEMBER OF b.idiomas")
    Long countByLanguage(@Param("language") String language);
}
