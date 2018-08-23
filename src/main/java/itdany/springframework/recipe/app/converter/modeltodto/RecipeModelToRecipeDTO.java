package itdany.springframework.recipe.app.converter.modeltodto;

import itdany.springframework.recipe.app.dto.RecipeDTO;
import itdany.springframework.recipe.app.model.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeModelToRecipeDTO implements Converter<Recipe, RecipeDTO> {

    private final CategoryModelToCategoryDTO categoryModelToCategoryDTO;
    private final IngredientModelToIngredientDTO ingredientModelToIngredientDTO;
    private final NotesModelToNotesDTO notesModelToNotesDTO;

    public RecipeModelToRecipeDTO(CategoryModelToCategoryDTO categoryModelToCategoryDTO,
                                  IngredientModelToIngredientDTO ingredientModelToIngredientDTO,
                                  NotesModelToNotesDTO notesModelToNotesDTO) {
        this.categoryModelToCategoryDTO = categoryModelToCategoryDTO;
        this.ingredientModelToIngredientDTO = ingredientModelToIngredientDTO;
        this.notesModelToNotesDTO = notesModelToNotesDTO;
    }

    @Override
    public RecipeDTO convert(Recipe source) {
        if (source == null)
            return null;

        final RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(source.getId());
        recipeDTO.setDescription(source.getDescription());
        recipeDTO.setDirection(source.getDirections());
        recipeDTO.setCookTime(source.getCookTime());
        recipeDTO.setPrepTime(source.getPrepTime());
        recipeDTO.setSource(source.getSource());
        recipeDTO.setDirection(source.getDirections());
        recipeDTO.setDifficulty(source.getDifficulty());
        recipeDTO.setServings(source.getServings());
        recipeDTO.setUrl(source.getUrl());
        recipeDTO.setNotes(notesModelToNotesDTO.convert(source.getNotes()));
        recipeDTO.setImage(source.getImage());

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(category -> {
                recipeDTO.getCategories().add(categoryModelToCategoryDTO.convert(category));
            });
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredient -> {
                recipeDTO.getIngredients().add(ingredientModelToIngredientDTO.convert(ingredient));
            });
        }

        return recipeDTO;
    }
}
