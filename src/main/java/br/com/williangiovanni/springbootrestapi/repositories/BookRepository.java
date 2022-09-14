package br.com.williangiovanni.springbootrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.williangiovanni.springbootrestapi.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
