package itdany.springframework.recipe.app.service.reactive;

import itdany.springframework.recipe.app.dto.IngredientDTO;
import reactor.core.publisher.Mono;

public interface IngredientReactiveService {
    Mono<IngredientDTO> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientDTO> saveIngredientDTO(IngredientDTO ingredientDTO);

    Mono<Void> deleteIngredientById(String recipeId, String ingredientId);
}
