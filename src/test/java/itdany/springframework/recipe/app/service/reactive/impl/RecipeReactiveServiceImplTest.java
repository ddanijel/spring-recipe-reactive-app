package itdany.springframework.recipe.app.service.reactive.impl;

import itdany.springframework.recipe.app.converter.dtotomodel.RecipeDTOToRecipeModel;
import itdany.springframework.recipe.app.converter.modeltodto.RecipeModelToRecipeDTO;
import itdany.springframework.recipe.app.dto.RecipeDTO;
import itdany.springframework.recipe.app.model.Recipe;
import itdany.springframework.recipe.app.repository.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RecipeReactiveServiceImplTest {

    RecipeReactiveServiceImpl recipeReactiveService;

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    RecipeDTOToRecipeModel recipeDTOToRecipeModel;

    @Mock
    RecipeModelToRecipeDTO recipeModelToRecipeDTO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeReactiveService = new RecipeReactiveServiceImpl(
                recipeReactiveRepository, recipeDTOToRecipeModel, recipeModelToRecipeDTO);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();

        when(recipeReactiveService.getRecipes()).thenReturn(Flux.just(recipe));

        List<Recipe> recipeList = recipeReactiveService.getRecipes().collectList().block();

        assertEquals(1, recipeList.size());
        verify(recipeReactiveRepository, times(1)).findAll();
        verify(recipeReactiveRepository, never()).findById(anyString());
    }

    @Test
    public void findById() {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        Recipe returnedRecipe = recipeReactiveService.findById("1").block();

        assertNotNull("Null recipe returned", returnedRecipe);
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, never()).findAll();
    }

    @Test
    public void findRecipeDtoById() {
        final String recipeId = "1";
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipeId);

        when(recipeModelToRecipeDTO.convert(any())).thenReturn(recipeDTO);

        RecipeDTO foundRecipeDTO = recipeReactiveService.findRecipeDtoById(recipeId).block();

        assertNotNull("Null recipe returned", foundRecipeDTO);
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, never()).findAll();
    }

    @Test
    public void deleteById() {
        String idToDelete = "1";

        when(recipeReactiveRepository.deleteById(anyString())).thenReturn(Mono.empty());

        recipeReactiveService.deleteById(idToDelete);

        verify(recipeReactiveRepository, times(1)).deleteById(anyString());
    }
}