package br.com.williangiovanni.springbootrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.williangiovanni.springbootrestapi.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
