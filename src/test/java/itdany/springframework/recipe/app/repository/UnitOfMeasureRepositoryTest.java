package itdany.springframework.recipe.app.repository;

import itdany.springframework.recipe.app.bootstrap.RecipeBootstrap;
import itdany.springframework.recipe.app.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureRepository.deleteAll();
        categoryRepository.deleteAll();
        recipeRepository.deleteAll();

        RecipeBootstrap recipeBootstrap = new RecipeBootstrap(
                categoryRepository, recipeRepository, unitOfMeasureRepository);
        recipeBootstrap.onApplicationEvent(null);
    }

    @Test
    public void findUnitOfMeasureByDescription() {
        Optional<UnitOfMeasure> unitOfMeasureOptional =
                unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");
        assertEquals("Teaspoon", unitOfMeasureOptional.get().getDescription());
    }
}