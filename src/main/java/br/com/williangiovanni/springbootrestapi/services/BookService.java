package br.com.williangiovanni.springbootrestapi.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.williangiovanni.springbootrestapi.controllers.BookController;
import br.com.williangiovanni.springbootrestapi.data.BookVO;
import br.com.williangiovanni.springbootrestapi.exceptions.RequiredObjectIsNullException;
import br.com.williangiovanni.springbootrestapi.exceptions.ResourceNotFoundException;
import br.com.williangiovanni.springbootrestapi.model.Book;
import br.com.williangiovanni.springbootrestapi.repositories.BookRepository;
import br.com.williangiovanni.springbootrestapi.utils.DozerMapper;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    private Logger logger = Logger.getLogger(BookService.class.getName());

    public List<BookVO> findAll() {
        logger.info("Finding Books");

        var persons = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
        persons.stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                });
        return persons;
    }

    public BookVO findById(Long id) throws Exception {

        logger.info("Finding Book");

        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO createBook(BookVO book) throws Exception {
        if (book == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Saving Book");

        var entity = DozerMapper.parseObject(book, Book.class);
        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO updateBook(BookVO book) throws Exception {
        if (book == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating Book");
        Book entity = bookRepository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(book.getLaunchDate());

        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void deleteBook(Long id) {
        logger.info("Deleting Book");
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        bookRepository.delete(entity);
    }

}
