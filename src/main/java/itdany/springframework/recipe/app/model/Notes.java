package itdany.springframework.recipe.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Notes {

    private String id;
    private Recipe recipe;
    private String recipeNotes;
}
