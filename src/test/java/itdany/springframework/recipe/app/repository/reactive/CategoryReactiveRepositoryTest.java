package itdany.springframework.recipe.app.repository.reactive;

import itdany.springframework.recipe.app.model.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    public static final String DESCRIPTION = "Desc";

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Category category = new Category();
        category.setDescription(DESCRIPTION);

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        assertEquals(Long.valueOf(1), count);
    }

    @Test
    public void findCategoryByDescription() {
        Category category = new Category();
        category.setDescription(DESCRIPTION);

        categoryReactiveRepository.save(category).then().block();

        Category savedCategory = categoryReactiveRepository.findCategoryByDescription(DESCRIPTION).block();

        assertNotNull(savedCategory.getId());
        assertEquals(DESCRIPTION, savedCategory.getDescription());
    }
}