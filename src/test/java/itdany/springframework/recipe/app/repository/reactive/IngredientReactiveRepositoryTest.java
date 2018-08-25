package itdany.springframework.recipe.app.repository.reactive;

import itdany.springframework.recipe.app.model.Ingredient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class IngredientReactiveRepositoryTest {

    public static final String DESCRIPTION = "Desc";

    @Autowired
    IngredientReactiveRepository ingredientReactiveRepository;

    @Before
    public void setUp() throws Exception {
        ingredientReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(DESCRIPTION);

        ingredientReactiveRepository.save(ingredient).block();

        assertEquals(Long.valueOf(1), ingredientReactiveRepository.count().block());
    }

}