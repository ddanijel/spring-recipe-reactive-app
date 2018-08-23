package itdany.springframework.recipe.app.converter.dtotomodel;

import itdany.springframework.recipe.app.dto.IngredientDTO;
import itdany.springframework.recipe.app.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientDTOToIngredientModel implements Converter<IngredientDTO, Ingredient> {

    private final UnitOfMeasureDTOToUnitOfMeasureModel unitOfMeasureDTOToUnitOfMeasureModel;

    public IngredientDTOToIngredientModel(UnitOfMeasureDTOToUnitOfMeasureModel unitOfMeasureDTOToUnitOfMeasureModel) {
        this.unitOfMeasureDTOToUnitOfMeasureModel = unitOfMeasureDTOToUnitOfMeasureModel;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientDTO source) {
        if (source == null)
            return null;

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());
        ingredient.setUnitOfMeasure(unitOfMeasureDTOToUnitOfMeasureModel.convert(source.getUnitOfMeasure()));
        return ingredient;
    }
}
