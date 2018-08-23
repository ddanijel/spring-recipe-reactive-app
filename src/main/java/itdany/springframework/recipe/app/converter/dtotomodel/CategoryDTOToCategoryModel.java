package itdany.springframework.recipe.app.converter.dtotomodel;

import itdany.springframework.recipe.app.dto.CategoryDTO;
import itdany.springframework.recipe.app.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTOToCategoryModel implements Converter<CategoryDTO, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryDTO source) {
        if (source == null)
            return null;

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
