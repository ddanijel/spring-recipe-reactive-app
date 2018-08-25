package itdany.springframework.recipe.app.service.reactive.impl;

import itdany.springframework.recipe.app.model.Recipe;
import itdany.springframework.recipe.app.repository.reactive.RecipeReactiveRepository;
import itdany.springframework.recipe.app.service.reactive.ImageReactiveService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ImageReactiveServiceImplTest {

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    ImageReactiveService imageReactiveService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageReactiveService = new ImageReactiveServiceImpl(recipeReactiveRepository);
    }

    @Test
    public void saveImageFile() throws IOException {
        String id = "1";
        MultipartFile multipartFile = new MockMultipartFile("imageFile", "testing.txt", "text/plain",
                "Spring reactive app".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(id);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        when(recipeReactiveRepository.save(any(Recipe.class))).thenReturn(Mono.just(recipe));

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        imageReactiveService.saveImageFile(id, multipartFile);

        verify(recipeReactiveRepository, times(1)).save(argumentCaptor.capture());
        Recipe saveRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, saveRecipe.getImage().length);
    }
}