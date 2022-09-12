package br.com.williangiovanni.springbootrestapi.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.williangiovanni.springbootrestapi.data.PersonVO;
import br.com.williangiovanni.springbootrestapi.data.PersonVOV2;
import br.com.williangiovanni.springbootrestapi.exceptions.ResourceNotFoundException;
import br.com.williangiovanni.springbootrestapi.model.Person;
import br.com.williangiovanni.springbootrestapi.repositories.PersonRepository;
import br.com.williangiovanni.springbootrestapi.utils.DozerMapper;
import br.com.williangiovanni.springbootrestapi.utils.PersonMapper;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<PersonVO> findAll() {
        logger.info("Finding people");

        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        logger.info("Finding person");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO createPerson(PersonVO person) {
        logger.info("Creating person");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVOV2 createPersonV2(PersonVOV2 person) {
        logger.info("Creating person with V2!");

        var entity = personMapper.convertVoToEntity(person);
        var vo = personMapper.convertEntityToVo(personRepository.save(entity));
        return vo;
    }

    public PersonVO updatePerson(PersonVO person) {
        logger.info("Updating person");
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public void deletePerson(Long id) {
        logger.info("Deleting person");
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        personRepository.delete(entity);
    }

}
