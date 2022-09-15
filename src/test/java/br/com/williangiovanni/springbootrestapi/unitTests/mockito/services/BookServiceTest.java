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
        assertEquals(1.0, result.getPrice());
        assertNotNull(result.getLaunchDate());

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

        var books = service.findAll();
        assertNotNull(books);
        assertEquals(14, books.size());

        var bookOne = books.get(1);
        assertNotNull(bookOne);
        assertNotNull(bookOne.getKey());
        assertNotNull(bookOne.getLinks());

        assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

        assertEquals("Title Test1", bookOne.getTitle());
        assertEquals("Author Test1", bookOne.getAuthor());
        assertEquals(1.0, bookOne.getPrice());
        assertNotNull(bookOne.getLaunchDate());

        var bookFour = books.get(4);
        assertNotNull(bookFour);
        assertNotNull(bookFour.getKey());
        assertNotNull(bookFour.getLinks());

        assertTrue(bookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));

        assertEquals("Title Test4", bookFour.getTitle());
        assertEquals("Author Test4", bookFour.getAuthor());
        assertEquals(4.0, bookFour.getPrice());
        assertNotNull(bookFour.getLaunchDate());

        var bookSeven = books.get(7);
        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getKey());
        assertNotNull(bookSeven.getLinks());

        assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));

        assertEquals("Title Test7", bookSeven.getTitle());
        assertEquals("Author Test7", bookSeven.getAuthor());
        assertEquals(7.0, bookSeven.getPrice());
        assertNotNull(bookSeven.getLaunchDate());

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
        assertEquals(1.0, result.getPrice());
        assertNotNull(result.getLaunchDate());

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
        assertEquals(1.0, result.getPrice());
        assertNotNull(result.getLaunchDate());
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
