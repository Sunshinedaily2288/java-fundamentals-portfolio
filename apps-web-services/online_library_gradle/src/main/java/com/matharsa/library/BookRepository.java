package com.matharsa.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // 💡 This magic naming convention tells Spring to write a "WHERE author = ?" query automatically!
    List<Book> findByAuthorContainingIgnoreCase(String author);
}
