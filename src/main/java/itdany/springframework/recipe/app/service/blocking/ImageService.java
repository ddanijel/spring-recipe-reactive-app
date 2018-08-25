package itdany.springframework.recipe.app.service.blocking;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(String recipeId, MultipartFile multipartFile);

}
