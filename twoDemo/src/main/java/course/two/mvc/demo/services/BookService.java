package course.two.mvc.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import course.two.mvc.demo.domain.Book;

public interface BookService {
    Iterable<Book> findAll();

}
