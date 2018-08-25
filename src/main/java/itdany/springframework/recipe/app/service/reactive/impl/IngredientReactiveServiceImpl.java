package itdany.springframework.recipe.app.service.reactive.impl;

import itdany.springframework.recipe.app.converter.dtotomodel.IngredientDTOToIngredientModel;
import itdany.springframework.recipe.app.converter.modeltodto.IngredientModelToIngredientDTO;
import itdany.springframework.recipe.app.dto.IngredientDTO;
import itdany.springframework.recipe.app.model.Ingredient;
import itdany.springframework.recipe.app.model.Recipe;
import itdany.springframework.recipe.app.repository.reactive.RecipeReactiveRepository;
import itdany.springframework.recipe.app.repository.reactive.UnitOfMeasureRectiveRepository;
import itdany.springframework.recipe.app.service.reactive.IngredientReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Slf4j
@Service
public class IngredientReactiveServiceImpl implements IngredientReactiveService {

    private final IngredientModelToIngredientDTO modelToIngredientDTO;
    private final IngredientDTOToIngredientModel dtoToIngredientModel;
    private final RecipeReactiveRepository recipeReactiveRepository;
    private final UnitOfMeasureRectiveRepository unitOfMeasureRectiveRepository;

    public IngredientReactiveServiceImpl(IngredientModelToIngredientDTO modelToIngredientDTO,
                                         IngredientDTOToIngredientModel dtoToIngredientModel,
                                         RecipeReactiveRepository recipeReactiveRepository,
                                         UnitOfMeasureRectiveRepository unitOfMeasureRectiveRepository) {
        this.modelToIngredientDTO = modelToIngredientDTO;
        this.dtoToIngredientModel = dtoToIngredientModel;
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.unitOfMeasureRectiveRepository = unitOfMeasureRectiveRepository;
    }

    @Override
    public Mono<IngredientDTO> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {
        return recipeReactiveRepository.findById(recipeId)
                .map(recipe -> recipe.getIngredients()
                        .stream()
                        .filter(ingredient -> ingredient.getId().equalsIgnoreCase(ingredientId))
                        .findFirst())
                .filter(Optional::isPresent)
                .map(ingredient -> {
                    IngredientDTO ingredientDTO = modelToIngredientDTO.convert(ingredient.get());
                    ingredientDTO.setRecipeId(recipeId);
                    return ingredientDTO;
                });
    }

    @Override
    public Mono<IngredientDTO> saveIngredientDTO(IngredientDTO ingredientDTO) {
        Optional<Recipe> recipeOptional = recipeReactiveRepository.findById(ingredientDTO.getRecipeId()).blockOptional();

        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found for id: " + ingredientDTO.getRecipeId());
            return Mono.just(new IngredientDTO());
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                // update ingredient
                Ingredient foundIngredient = ingredientOptional.get();
                foundIngredient.setDescription(ingredientDTO.getDescription());
                foundIngredient.setAmount(ingredientDTO.getAmount());
                foundIngredient.setUnitOfMeasure(unitOfMeasureRectiveRepository
                        .findById(ingredientDTO.getUnitOfMeasure().getId()).block());

                if (foundIngredient.getUnitOfMeasure() == null) {
                    throw new RuntimeException("Unit Of Measure Not Found");
                }
            } else {
                // add new ingredient
                Ingredient ingredient = dtoToIngredientModel.convert(ingredientDTO);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();


            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
                    .findFirst();

            if (!savedIngredientOptional.isPresent()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getDescription().equals(ingredientDTO.getDescription()))
                        .filter(ingredient -> ingredient.getAmount().equals(ingredientDTO.getAmount()))
                        .filter(ingredient -> ingredient.getUnitOfMeasure().getId().equals(ingredientDTO.getUnitOfMeasure().getId()))
                        .findFirst();
            }

            IngredientDTO savedIngredientDto = modelToIngredientDTO.convert(savedIngredientOptional.get());
            savedIngredientDto.setRecipeId(recipe.getId());

            return Mono.just(savedIngredientDto);
        }
    }

    @Override
    public Mono<Void> deleteIngredientById(String recipeId, String ingredientId) {
        log.debug("Deleting ingredient - recipeId: " + recipeId + ", ingredientId: " + ingredientId);

        Recipe recipeReactive = recipeReactiveRepository.findById(recipeId).block();

        if (recipeReactive != null) {
            log.debug("Recipe found");

            Optional<Ingredient> ingredientOptional = recipeReactive
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                log.debug("Ingredient found");
                recipeReactive.getIngredients().remove(ingredientOptional.get());
                recipeReactiveRepository.save(recipeReactive);
            }
        } else {
            log.debug("Recipe not found with id: " + recipeId);
        }
        return Mono.empty();
    }
}
