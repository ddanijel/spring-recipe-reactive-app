package itdany.springframework.recipe.app.converter.modeltodto;

import itdany.springframework.recipe.app.dto.CategoryDTO;
import itdany.springframework.recipe.app.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryModelToCategoryDTO implements Converter<Category, CategoryDTO> {

    @Synchronized
    @Nullable
    @Override
    public CategoryDTO convert(Category source) {
        if (source == null)
            return null;

        final CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(source.getId());
        categoryDTO.setDescription(source.getDescription());
        return categoryDTO;
    }
}
