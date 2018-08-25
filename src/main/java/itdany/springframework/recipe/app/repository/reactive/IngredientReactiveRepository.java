package itdany.springframework.recipe.app.repository.reactive;

import itdany.springframework.recipe.app.model.Ingredient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IngredientReactiveRepository extends ReactiveMongoRepository<Ingredient, String> {
}
