package br.com.williangiovanni.springbootrestapi.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.williangiovanni.springbootrestapi.controllers.PersonController;
import br.com.williangiovanni.springbootrestapi.data.PersonVO;
import br.com.williangiovanni.springbootrestapi.exceptions.RequiredObjectIsNullException;
import br.com.williangiovanni.springbootrestapi.exceptions.ResourceNotFoundException;
import br.com.williangiovanni.springbootrestapi.model.Person;
import br.com.williangiovanni.springbootrestapi.repositories.PersonRepository;
import br.com.williangiovanni.springbootrestapi.utils.DozerMapper;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<PersonVO> findAll() {
        logger.info("Finding people");

        var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        persons.stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                });
        return persons;
    }

    public PersonVO findById(Long id) throws Exception {

        logger.info("Finding person");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO createPerson(PersonVO person) throws Exception {
        if (person == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating person");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO updatePerson(PersonVO person) throws Exception {
        if (person == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating person");
        Person entity = personRepository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void deletePerson(Long id) {
        logger.info("Deleting person");
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        personRepository.delete(entity);
    }

}
