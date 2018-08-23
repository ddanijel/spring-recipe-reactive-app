package itdany.springframework.recipe.app.converter.dtotomodel;

import itdany.springframework.recipe.app.dto.RecipeDTO;
import itdany.springframework.recipe.app.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeDTOToRecipeModel implements Converter<RecipeDTO, Recipe> {

    private final CategoryDTOToCategoryModel categoryDTOToCategoryModel;
    private final IngredientDTOToIngredientModel ingredientDTOToIngredientModel;
    private final NotesDTOToNotesModel notesDTOToNotesModel;

    public RecipeDTOToRecipeModel(CategoryDTOToCategoryModel categoryDTOToCategoryModel,
                                  IngredientDTOToIngredientModel ingredientDTOToIngredientModel,
                                  NotesDTOToNotesModel notesDTOToNotesModel) {
        this.categoryDTOToCategoryModel = categoryDTOToCategoryModel;
        this.ingredientDTOToIngredientModel = ingredientDTOToIngredientModel;
        this.notesDTOToNotesModel = notesDTOToNotesModel;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeDTO source) {
        if (source == null)
            return null;

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDirections(source.getDirection());
        recipe.setDirections(source.getDirection());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setNotes(notesDTOToNotesModel.convert(source.getNotes()));
        recipe.setDifficulty(source.getDifficulty());

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(categoryDTO -> {
                recipe.getCategories().add(categoryDTOToCategoryModel.convert(categoryDTO));
            });
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredientDTO -> {
                recipe.getIngredients().add(ingredientDTOToIngredientModel.convert(ingredientDTO));
            });
        }

        return recipe;
    }
}
