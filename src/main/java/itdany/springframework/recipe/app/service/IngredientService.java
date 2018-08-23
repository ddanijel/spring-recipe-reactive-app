package itdany.springframework.recipe.app.service;

import itdany.springframework.recipe.app.dto.IngredientDTO;

public interface IngredientService {
    IngredientDTO findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientDTO saveIngredientDTO(IngredientDTO ingredientDTO);

    void deleteIngredientById(String recipeId, String ingredientId);
}
