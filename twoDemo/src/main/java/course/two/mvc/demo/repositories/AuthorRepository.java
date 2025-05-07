package course.two.mvc.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import course.two.mvc.demo.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
