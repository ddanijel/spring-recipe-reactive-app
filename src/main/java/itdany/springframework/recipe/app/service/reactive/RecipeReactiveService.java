package itdany.springframework.recipe.app.service.reactive;

import itdany.springframework.recipe.app.dto.RecipeDTO;
import itdany.springframework.recipe.app.model.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeReactiveService {

    Flux<Recipe> getRecipes();

    Mono<Recipe> findById(String id);

    Mono<RecipeDTO> saveRecipeDTO(RecipeDTO recipeDTO);

    Mono<RecipeDTO> findRecipeDtoById(String id);

    Void deleteById(String id);
}
