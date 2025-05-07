package course.two.mvc.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import course.two.mvc.demo.domain.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
