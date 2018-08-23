package itdany.springframework.recipe.app.service.impl;

import itdany.springframework.recipe.app.model.Recipe;
import itdany.springframework.recipe.app.repository.RecipeRepository;
import itdany.springframework.recipe.app.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(String recipeId, MultipartFile multipartFile) {
        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();
            Byte[] bytes = new Byte[multipartFile.getBytes().length];

            int i = 0;

            for (byte b : multipartFile.getBytes()) {
                bytes[i++] = b;
            }

            recipe.setImage(bytes);
            recipeRepository.save(recipe);

        } catch (IOException e) {
            log.error("Error while saving image file occured: " + e);
            e.printStackTrace();
        }
    }
}
