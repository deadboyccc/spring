package course.two.mvc.demo.services;

import org.springframework.stereotype.Service;

import course.two.mvc.demo.domain.Book;
import course.two.mvc.demo.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
