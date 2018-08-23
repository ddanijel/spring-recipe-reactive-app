package itdany.springframework.recipe.app.controller;

import itdany.springframework.recipe.app.dto.IngredientDTO;
import itdany.springframework.recipe.app.dto.RecipeDTO;
import itdany.springframework.recipe.app.service.IngredientService;
import itdany.springframework.recipe.app.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = "/recipes/{recipeId}/ingredients")
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public Set<IngredientDTO> getIngredients(@PathVariable String recipeId) {
        log.debug("Getting ingredients for the recipe with id: " + recipeId);
        return recipeService.findRecipeDtoById(recipeId).getIngredients();
    }

    @GetMapping("/{ingredientId}")
    public IngredientDTO getIngredientDto(@PathVariable String recipeId,
                                          @PathVariable String ingredientId) {
        return ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId);
    }

    @PostMapping
    public IngredientDTO saveUpdateIngredient(@PathVariable String recipeId,
                                              @RequestBody IngredientDTO ingredientDTO) {

        // todo handle error if the recipe is not present
        RecipeDTO recipeDTO = recipeService.findRecipeDtoById(recipeId);

        return ingredientService.saveIngredientDTO(ingredientDTO);
    }


    @DeleteMapping("/{ingredientId}")
    public void deleteIngredient(@PathVariable String recipeId,
                                 @PathVariable String ingredientId) {
        ingredientService.deleteIngredientById(recipeId, ingredientId);
    }
}
