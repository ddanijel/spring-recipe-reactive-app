package itdany.springframework.recipe.app.converter.modeltodto;

import itdany.springframework.recipe.app.dto.UnitOfMeasureDTO;
import itdany.springframework.recipe.app.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureModelToUnitOfMeasureDTO implements Converter<UnitOfMeasure, UnitOfMeasureDTO> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureDTO convert(UnitOfMeasure source) {
        if (source == null)
            return null;

        final UnitOfMeasureDTO unitOfMeasureDTO = new UnitOfMeasureDTO();
        unitOfMeasureDTO.setId(source.getId());
        unitOfMeasureDTO.setDescription(source.getDescription());
        return unitOfMeasureDTO;
    }
}
