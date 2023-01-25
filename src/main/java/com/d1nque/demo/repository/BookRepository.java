package com.d1nque.demo.repository;

import com.d1nque.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksByCategory_CategoryName(String categoryName);

    List<Book> findBooksByAuthorAndCategory_CategoryName(String author, String categoryName);

    @Modifying
    @Query("delete from Book b where b.id=:id")
    void deleteBooks(@Param("id") Long id);

    @Modifying
    @Query("update Book b set b.title=:#{#book.title}, b.author=:#{#book.author} where b.id=:#{#book.id}")
    void updateBooks(@Param("book") Book book);
}
