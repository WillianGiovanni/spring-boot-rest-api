package br.com.williangiovanni.springbootrestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.williangiovanni.springbootrestapi.data.PersonVO;
import br.com.williangiovanni.springbootrestapi.services.PersonService;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findByAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
        return personService.findById(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO createPerson(@RequestBody PersonVO person) {
        return personService.createPerson(person);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO updatePerson(@RequestBody PersonVO person) {
        return personService.updatePerson(person);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) throws Exception {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
