package itdany.springframework.recipe.app.service.impl;

import itdany.springframework.recipe.app.converter.dtotomodel.IngredientDTOToIngredientModel;
import itdany.springframework.recipe.app.converter.modeltodto.IngredientModelToIngredientDTO;
import itdany.springframework.recipe.app.dto.IngredientDTO;
import itdany.springframework.recipe.app.model.Ingredient;
import itdany.springframework.recipe.app.model.Recipe;
import itdany.springframework.recipe.app.repository.IngredientRepository;
import itdany.springframework.recipe.app.repository.RecipeRepository;
import itdany.springframework.recipe.app.repository.UnitOfMeasureRepository;
import itdany.springframework.recipe.app.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientModelToIngredientDTO ingredientModelToIngredientDTO;
    private final IngredientDTOToIngredientModel ingredientDTOToIngredientModel;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientRepository ingredientRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository,
                                 IngredientModelToIngredientDTO ingredientModelToIngredientDTO,
                                 IngredientDTOToIngredientModel ingredientDTOToIngredientModel) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientModelToIngredientDTO = ingredientModelToIngredientDTO;
        this.ingredientDTOToIngredientModel = ingredientDTOToIngredientModel;
    }

    @Override
    public IngredientDTO findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            //todo impl error handling
            log.error("recipe not found with id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientDTO> ingredientDTOOptional = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientModelToIngredientDTO.convert(ingredient)).findFirst();

        if (!ingredientDTOOptional.isPresent()) {
            //todo impl error handling
            log.error("ingredient not found with id: " + ingredientId);
        }
        return ingredientDTOOptional.get();
    }

    @Override
    public IngredientDTO saveIngredientDTO(IngredientDTO ingredientDTO) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientDTO.getRecipeId());

        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found for id: " + ingredientDTO.getRecipeId());
            return new IngredientDTO();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientDTO.getDescription());
                ingredientFound.setAmount(ingredientDTO.getAmount());
                ingredientFound.setUnitOfMeasure(
                        unitOfMeasureRepository
                                .findById(ingredientDTO.getUnitOfMeasure().getId())
                                .orElseThrow(() ->
                                        new RuntimeException("Unit of measure not found for id: " +
                                                ingredientDTO.getUnitOfMeasure().getId())));
            } else {
                Ingredient ingredient = ingredientDTOToIngredientModel.convert(ingredientDTO);
                recipe.addIngredient(ingredient);
            }

            Recipe saveRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = saveRecipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
                    .findFirst();

            if (!savedIngredientOptional.isPresent()) {
                savedIngredientOptional = saveRecipe.getIngredients()
                        .stream()
                        .filter(ingredient -> ingredient.getDescription().equals(ingredientDTO.getDescription()))
                        .filter(ingredient -> ingredient.getAmount().equals(ingredientDTO.getAmount()))
                        .filter(ingredient -> ingredient.getUnitOfMeasure().getId().equals(ingredientDTO.getUnitOfMeasure().getId()))
                        .findFirst();
            }

            return ingredientModelToIngredientDTO.convert(savedIngredientOptional.get());
        }
    }

    @Override
    public void deleteIngredientById(String recipeId, String ingredientId) {

        log.debug("Deleting ingredient, recipeId: " + recipeId + ", ingredientId: " + ingredientId);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            log.debug("Recipe found.");

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                log.debug("Ingredient found");
                Ingredient ingredientToDelete = ingredientOptional.get();
                recipe.getIngredients().remove(ingredientToDelete);
                recipeRepository.save(recipe);
                ingredientRepository.delete(ingredientToDelete);
            } else {
                log.debug("Ingredient not found for id: " + ingredientId);
            }
        } else {
            log.debug("Recipe not found for id: " + recipeId);
        }

    }


}
