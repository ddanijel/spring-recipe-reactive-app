package itdany.springframework.recipe.app.service.reactive.impl;

import itdany.springframework.recipe.app.converter.dtotomodel.IngredientDTOToIngredientModel;
import itdany.springframework.recipe.app.converter.dtotomodel.UnitOfMeasureDTOToUnitOfMeasureModel;
import itdany.springframework.recipe.app.converter.modeltodto.IngredientModelToIngredientDTO;
import itdany.springframework.recipe.app.converter.modeltodto.UnitOfMeasureModelToUnitOfMeasureDTO;
import itdany.springframework.recipe.app.dto.IngredientDTO;
import itdany.springframework.recipe.app.dto.UnitOfMeasureDTO;
import itdany.springframework.recipe.app.model.Ingredient;
import itdany.springframework.recipe.app.model.Recipe;
import itdany.springframework.recipe.app.repository.reactive.RecipeReactiveRepository;
import itdany.springframework.recipe.app.repository.reactive.UnitOfMeasureRectiveRepository;
import itdany.springframework.recipe.app.service.reactive.IngredientReactiveService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class IngredientReactiveServiceImplTest {

    private final IngredientModelToIngredientDTO modelToIngredientDTO;
    private final IngredientDTOToIngredientModel dtoToIngredientModel;

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    UnitOfMeasureRectiveRepository unitOfMeasureRectiveRepository;

    IngredientReactiveService ingredientReactiveService;

    public IngredientReactiveServiceImplTest() {
        this.modelToIngredientDTO = new IngredientModelToIngredientDTO(new UnitOfMeasureModelToUnitOfMeasureDTO());
        this.dtoToIngredientModel = new IngredientDTOToIngredientModel(new UnitOfMeasureDTOToUnitOfMeasureModel());
    }


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientReactiveService = new IngredientReactiveServiceImpl(
                modelToIngredientDTO, dtoToIngredientModel, recipeReactiveRepository, unitOfMeasureRectiveRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("2");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId("3");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        IngredientDTO ingredientDTO = ingredientReactiveService.findByRecipeIdAndIngredientId("1", "2").block();

        assertEquals("2", ingredientDTO.getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
    }

    @Test
    public void saveIngredientDTO() {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId("1");
        ingredientDTO.setRecipeId("2");
        ingredientDTO.setUnitOfMeasure(new UnitOfMeasureDTO());
        ingredientDTO.getUnitOfMeasure().setId("3");

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId("2");
        savedRecipe.addIngredient(new Ingredient());

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(savedRecipe));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(savedRecipe));

        IngredientDTO savedIngredientDTO = ingredientReactiveService.saveIngredientDTO(ingredientDTO).block();

        assertEquals("1", savedIngredientDTO.getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void deleteIngredientById() {

        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId("2");
        recipe.addIngredient(ingredient);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(recipe));

        ingredientReactiveService.deleteIngredientById("1", "2");

        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));

    }
}