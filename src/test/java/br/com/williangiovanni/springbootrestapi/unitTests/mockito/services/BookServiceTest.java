package br.com.williangiovanni.springbootrestapi.unitTests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.williangiovanni.springbootrestapi.data.BookVO;
import br.com.williangiovanni.springbootrestapi.exceptions.RequiredObjectIsNullException;
import br.com.williangiovanni.springbootrestapi.model.Book;
import br.com.williangiovanni.springbootrestapi.repositories.BookRepository;
import br.com.williangiovanni.springbootrestapi.services.BookService;
import br.com.williangiovanni.springbootrestapi.unitTest.mapper.mock.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setupMocks() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBook() throws Exception {
        Book entity = input.mockEntity(1);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.createBook(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

        assertEquals("Title Test1", result.getTitle());
        assertEquals("Author Test1", result.getAuthor());
        assertEquals("1", result.getPrice());
        assertEquals(new Date(), result.getLaunchDate());

    }

    @Test
    void testCreateBookWithNull() throws Exception {

        Exception ex = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.createBook(null);
        });

        String expectedMessage = "Não é possível armazenar um objeto vazio";
        String actualMessage = ex.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testDeleteBook() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.deleteBook(1L);
    }

    @Test
    void testFindAll() {
        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();
        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());

        assertTrue(personOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

        assertEquals("Title Test1", personOne.getTitle());
        assertEquals("Author Test1", personOne.getAuthor());
        assertEquals("1", personOne.getPrice());
        assertEquals(new Date(), personOne.getLaunchDate());

        var personFour = people.get(4);
        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());

        assertTrue(personFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));

        assertEquals("Title Test4", personFour.getTitle());
        assertEquals("Author Test4", personFour.getAuthor());
        assertEquals("Last Name Test4", personFour.getPrice());
        assertEquals("Male", personFour.getLaunchDate());

        var personSeven = people.get(7);
        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());

        assertTrue(personSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));

        assertEquals("Title Test7", personSeven.getTitle());
        assertEquals("Author Test7", personSeven.getAuthor());
        assertEquals("Last Name Test7", personSeven.getPrice());
        assertEquals(new Date(), personSeven.getLaunchDate());

    }

    @Test
    void testFindById() throws Exception {
        Book entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

        assertEquals("Title Test1", result.getTitle());
        assertEquals("Author Test1", result.getAuthor());
        assertEquals("1", result.getPrice());
        assertEquals(new Date(), result.getLaunchDate());

    }

    @Test
    void testBookPerson() throws Exception {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.updateBook(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

        assertEquals("Title Test1", result.getTitle());
        assertEquals("Author Test1", result.getAuthor());
        assertEquals("1", result.getPrice());
        assertEquals(new Date(), result.getLaunchDate());
    }

    @Test
    void testUpdateBookWithNull() throws Exception {

        Exception ex = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.updateBook(null);
        });

        String expectedMessage = "Não é possível armazenar um objeto vazio";
        String actualMessage = ex.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}
