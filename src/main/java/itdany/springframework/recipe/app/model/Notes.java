package itdany.springframework.recipe.app.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
public class Notes {

    @Id
    private String id;

    @DBRef
    private Recipe recipe;
    private String recipeNotes;
}
