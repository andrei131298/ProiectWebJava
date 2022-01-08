package ro.unibuc.tennistournaments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.tennistournaments.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
