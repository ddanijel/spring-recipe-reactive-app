package itdany.springframework.recipe.app.service.reactive.impl;

import itdany.springframework.recipe.app.model.Recipe;
import itdany.springframework.recipe.app.repository.reactive.RecipeReactiveRepository;
import itdany.springframework.recipe.app.service.reactive.ImageReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
public class ImageReactiveServiceImpl implements ImageReactiveService {

    private final RecipeReactiveRepository recipeReactiveRepository;

    public ImageReactiveServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public Mono<Void> saveImageFile(String recipeId, MultipartFile multipartFile) {

        Mono<Recipe> recipeMono = recipeReactiveRepository.findById(recipeId)
                .map(recipe -> {
                    Byte[] bytes;
                    try {
                        bytes = new Byte[multipartFile.getBytes().length];

                        int i = 0;

                        for (byte b : multipartFile.getBytes()) {
                            bytes[i++] = b;
                        }

                        recipe.setImage(bytes);
                        return recipe;

                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });
        recipeReactiveRepository.save(recipeMono.block()).block();
        return Mono.empty();
    }
}
