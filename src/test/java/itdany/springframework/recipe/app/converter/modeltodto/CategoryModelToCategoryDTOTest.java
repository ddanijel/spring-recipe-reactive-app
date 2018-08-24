package itdany.springframework.recipe.app.converter.modeltodto;

import itdany.springframework.recipe.app.dto.CategoryDTO;
import itdany.springframework.recipe.app.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryModelToCategoryDTOTest {

    public static final String ID_VALUE = "1";
    public static final String DESCRIPTION = "Desc";
    CategoryModelToCategoryDTO toCategoryDTOConverter;

    @Before
    public void setUp() throws Exception {
        toCategoryDTOConverter = new CategoryModelToCategoryDTO();
    }

    @Test
    public void testNullObject() {
        assertNull(toCategoryDTOConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(toCategoryDTOConverter.convert(new Category()));
    }

    @Test
    public void convert() {
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        CategoryDTO categoryDTO = toCategoryDTOConverter.convert(category);

        assertEquals(ID_VALUE, categoryDTO.getId());
        assertEquals(DESCRIPTION, categoryDTO.getDescription());
    }
}