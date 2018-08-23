package itdany.springframework.recipe.app.dto;

import itdany.springframework.recipe.app.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDTO {
    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private Difficulty difficulty;
    private NotesDTO notes;
    private String source;
    private String url;
    private String direction;
    private Byte[] image;
    private Set<IngredientDTO> ingredients = new HashSet<>();
    private Set<CategoryDTO> categories = new HashSet<>();
}
