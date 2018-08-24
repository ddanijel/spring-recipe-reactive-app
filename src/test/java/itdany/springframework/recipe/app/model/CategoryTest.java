package itdany.springframework.recipe.app.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void getId() {
        String idValue = "1";
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
        String description = "Desc";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }

    @Test
    public void getRecipes() {
    }
}