package itdany.springframework.recipe.app.repository.blocking;

import itdany.springframework.recipe.app.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, String> {

}
