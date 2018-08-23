package itdany.springframework.recipe.app.service.impl;

import itdany.springframework.recipe.app.converter.dtotomodel.RecipeDTOToRecipeModel;
import itdany.springframework.recipe.app.converter.modeltodto.RecipeModelToRecipeDTO;
import itdany.springframework.recipe.app.dto.RecipeDTO;
import itdany.springframework.recipe.app.exception.NotFoundException;
import itdany.springframework.recipe.app.model.Recipe;
import itdany.springframework.recipe.app.repository.RecipeRepository;
import itdany.springframework.recipe.app.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDTOToRecipeModel recipeDTOToRecipeModel;
    private final RecipeModelToRecipeDTO recipeModelToRecipeDTO;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeDTOToRecipeModel recipeDTOToRecipeModel,
                             RecipeModelToRecipeDTO recipeModelToRecipeDTO) {
        this.recipeRepository = recipeRepository;
        this.recipeDTOToRecipeModel = recipeDTOToRecipeModel;
        this.recipeModelToRecipeDTO = recipeModelToRecipeDTO;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("getRecipe() called...");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(String id) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        return recipeOptional.orElseThrow(() -> new NotFoundException("Recipe not found."));
    }

    @Override
    @Transactional
    public RecipeDTO saveRecipeDTO(RecipeDTO recipeDTO) {
        return recipeModelToRecipeDTO.convert(recipeRepository.save(recipeDTOToRecipeModel.convert(recipeDTO)));
    }

    @Override
    @Transactional
    public RecipeDTO findRecipeDtoById(String id) {
        return recipeModelToRecipeDTO.convert(findById(id));
    }

    @Override
    public void deleteById(String id) {
        recipeRepository.deleteById(id);
    }

}
