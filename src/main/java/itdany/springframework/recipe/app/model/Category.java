package itdany.springframework.recipe.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
public class Category {

    private String id;
    private String description;
    private Set<Recipe> recipes = new HashSet<>();
}
