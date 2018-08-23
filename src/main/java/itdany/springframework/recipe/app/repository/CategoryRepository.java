package itdany.springframework.recipe.app.repository;

import itdany.springframework.recipe.app.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
    Optional<Category> findCategoryByDescription(String description);
}
