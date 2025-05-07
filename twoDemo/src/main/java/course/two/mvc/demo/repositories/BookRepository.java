package course.two.mvc.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import course.two.mvc.demo.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
