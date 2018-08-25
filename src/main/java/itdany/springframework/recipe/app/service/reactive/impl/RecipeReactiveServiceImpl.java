package itdany.springframework.recipe.app.service.reactive.impl;

import itdany.springframework.recipe.app.converter.dtotomodel.RecipeDTOToRecipeModel;
import itdany.springframework.recipe.app.converter.modeltodto.RecipeModelToRecipeDTO;
import itdany.springframework.recipe.app.dto.RecipeDTO;
import itdany.springframework.recipe.app.model.Recipe;
import itdany.springframework.recipe.app.repository.reactive.RecipeReactiveRepository;
import itdany.springframework.recipe.app.service.reactive.RecipeReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RecipeReactiveServiceImpl implements RecipeReactiveService {

    private final RecipeReactiveRepository recipeReactiveRepository;
    private final RecipeDTOToRecipeModel recipeDTOToRecipeModel;
    private final RecipeModelToRecipeDTO recipeModelToRecipeDTO;

    public RecipeReactiveServiceImpl(RecipeReactiveRepository recipeReactiveRepository,
                                     RecipeDTOToRecipeModel recipeDTOToRecipeModel,
                                     RecipeModelToRecipeDTO recipeModelToRecipeDTO) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.recipeDTOToRecipeModel = recipeDTOToRecipeModel;
        this.recipeModelToRecipeDTO = recipeModelToRecipeDTO;
    }

    @Override
    public Flux<Recipe> getRecipes() {
        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {
        return recipeReactiveRepository.findById(id);
    }

    @Override
    public Mono<RecipeDTO> saveRecipeDTO(RecipeDTO recipeDTO) {
//        return Mono.just(
//                recipeModelToRecipeDTO.convert(
//                        recipeReactiveRepository.save(recipeDTOToRecipeModel.convert(recipeDTO)).block()));
        return recipeReactiveRepository.save(recipeDTOToRecipeModel.convert(recipeDTO))
                .map(recipeModelToRecipeDTO::convert);
    }

    @Override
    public Mono<RecipeDTO> findRecipeDtoById(String id) {
        return recipeReactiveRepository.findById(id)
                .map(recipe -> {
                    RecipeDTO recipeDTO = recipeModelToRecipeDTO.convert(recipe);
                    recipeDTO.getIngredients().forEach(ingredientDTO -> {
                        ingredientDTO.setRecipeId(recipeDTO.getId());
                    });
                    return recipeDTO;
                });
    }

    @Override
    public Void deleteById(String id) {
        return recipeReactiveRepository.deleteById(id).block();
    }
}
