package itdany.springframework.recipe.app.controller;

import itdany.springframework.recipe.app.dto.RecipeDTO;
import itdany.springframework.recipe.app.service.ImageService;
import itdany.springframework.recipe.app.service.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

    // todo testing

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping(value = "/recipes/{recipeId}/images", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] renderImageFromDB(@PathVariable String recipeId) {
        RecipeDTO recipeDTO = recipeService.findRecipeDtoById(recipeId);

        byte[] imageBytes = new byte[recipeDTO.getImage().length];

        int i = 0;

        for (Byte wrappedByte : recipeDTO.getImage()) {
            imageBytes[i++] = wrappedByte; // auto unboxing
        }

        return imageBytes;
    }

    @PostMapping("/recipes/{recipeId}/images")
    public void uploadImage(@PathVariable String recipeId,
                            @RequestParam("imageFile") MultipartFile imageFile) {
        imageService.saveImageFile(recipeId, imageFile);
    }

}
