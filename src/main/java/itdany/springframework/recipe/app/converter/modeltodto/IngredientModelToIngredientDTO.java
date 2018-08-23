package itdany.springframework.recipe.app.converter.modeltodto;

import itdany.springframework.recipe.app.dto.IngredientDTO;
import itdany.springframework.recipe.app.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientModelToIngredientDTO implements Converter<Ingredient, IngredientDTO> {

    private final UnitOfMeasureModelToUnitOfMeasureDTO unitOfMeasureModelToUnitOfMeasureDTO;

    public IngredientModelToIngredientDTO(UnitOfMeasureModelToUnitOfMeasureDTO unitOfMeasureModelToUnitOfMeasureDTO) {
        this.unitOfMeasureModelToUnitOfMeasureDTO = unitOfMeasureModelToUnitOfMeasureDTO;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientDTO convert(Ingredient source) {
        if (source == null)
            return null;

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(source.getId());
        ingredientDTO.setDescription(source.getDescription());
        ingredientDTO.setAmount(source.getAmount());
        if (source.getRecipe() != null) {
            ingredientDTO.setRecipeId(source.getRecipe().getId());
        }
        ingredientDTO.setUnitOfMeasure(unitOfMeasureModelToUnitOfMeasureDTO.convert(source.getUnitOfMeasure()));
        return ingredientDTO;
    }
}
