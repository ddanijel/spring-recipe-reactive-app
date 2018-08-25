package itdany.springframework.recipe.app.repository.reactive;

import itdany.springframework.recipe.app.model.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
