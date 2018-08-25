package itdany.springframework.recipe.app.repository.blocking;

import itdany.springframework.recipe.app.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
