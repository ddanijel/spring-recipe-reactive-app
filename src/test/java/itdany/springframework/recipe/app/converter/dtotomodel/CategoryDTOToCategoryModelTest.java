package itdany.springframework.recipe.app.converter.dtotomodel;

import itdany.springframework.recipe.app.dto.CategoryDTO;
import itdany.springframework.recipe.app.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryDTOToCategoryModelTest {

    public static final String ID_VALUE = "1";
    public static final String DESCRIPTION = "Desc";
    CategoryDTOToCategoryModel dtoToCategoryModelConverter;

    @Before
    public void setUp() throws Exception {
        dtoToCategoryModelConverter = new CategoryDTOToCategoryModel();
    }

    @Test
    public void testNullObject() {
        assertNull(dtoToCategoryModelConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(dtoToCategoryModelConverter.convert(new CategoryDTO()));
    }

    @Test
    public void convert() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID_VALUE);
        categoryDTO.setDescription(DESCRIPTION);

        Category category = dtoToCategoryModelConverter.convert(categoryDTO);

        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}