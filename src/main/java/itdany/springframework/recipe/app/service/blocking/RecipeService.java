package itdany.springframework.recipe.app.service.blocking;

import itdany.springframework.recipe.app.dto.RecipeDTO;
import itdany.springframework.recipe.app.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(String id);

    RecipeDTO saveRecipeDTO(RecipeDTO recipeDTO);

    RecipeDTO findRecipeDtoById(String id);

    void deleteById(String id);
}
