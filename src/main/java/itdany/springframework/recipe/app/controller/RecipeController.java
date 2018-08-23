package itdany.springframework.recipe.app.controller;

import itdany.springframework.recipe.app.dto.RecipeDTO;
import itdany.springframework.recipe.app.model.Recipe;
import itdany.springframework.recipe.app.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = "/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public Set<Recipe> getAllRecipes() {
        log.debug("Getting all recipes");
        return recipeService.getRecipes();
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable String id) {
        return recipeService.findById(id);
    }

    @PostMapping
    public RecipeDTO saveRecipe(@Valid @RequestBody RecipeDTO recipeDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }

        return recipeService.saveRecipeDTO(recipeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable String id) {
        recipeService.deleteById(id);
        return ResponseEntity.ok("Recipe deleted.");
    }

}
