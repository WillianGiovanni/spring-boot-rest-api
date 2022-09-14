package br.com.williangiovanni.springbootrestapi.unitTest.mapper.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.williangiovanni.springbootrestapi.data.BookVO;
import br.com.williangiovanni.springbootrestapi.model.Book;

public class MockBook {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> persons = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setTitle("Title Test" + number);
        book.setAuthor("Author Test" + number);
        book.setPrice(number);
        book.setId(number.longValue());
        book.setLaunchDate(new Date());
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO bookVo = new BookVO();
        bookVo.setTitle("Title Test" + number);
        bookVo.setAuthor("Author Test" + number);
        bookVo.setPrice(number);
        bookVo.setKey(number.longValue());
        bookVo.setLaunchDate(new Date());
        return bookVo;
    }

}
