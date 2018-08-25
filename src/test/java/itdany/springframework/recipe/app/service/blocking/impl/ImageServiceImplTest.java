package itdany.springframework.recipe.app.service.blocking.impl;

import itdany.springframework.recipe.app.model.Recipe;
import itdany.springframework.recipe.app.repository.blocking.RecipeRepository;
import itdany.springframework.recipe.app.service.blocking.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    ImageService imageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImageFile() throws IOException {
        String id = "1";
        MultipartFile multipartFile = new MockMultipartFile(
                "imageFile", "testing.txt",
                "text.plain", "itdany test content".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(id);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(id, multipartFile);

        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe saveRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, saveRecipe.getImage().length);
    }
}