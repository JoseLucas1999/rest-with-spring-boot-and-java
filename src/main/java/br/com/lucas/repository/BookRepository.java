package br.com.lucas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lucas.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
//	Object save(BookDTO entity);
}
