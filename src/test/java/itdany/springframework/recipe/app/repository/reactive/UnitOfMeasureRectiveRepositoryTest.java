package itdany.springframework.recipe.app.repository.reactive;

import itdany.springframework.recipe.app.model.UnitOfMeasure;
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
public class UnitOfMeasureRectiveRepositoryTest {

    public static final String DESCRIPTION = "Desc";

    @Autowired
    UnitOfMeasureRectiveRepository unitOfMeasureRectiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureRectiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(DESCRIPTION);

        unitOfMeasureRectiveRepository.save(unitOfMeasure).block();

        assertEquals(Long.valueOf(1), unitOfMeasureRectiveRepository.count().block());

    }

    @Test
    public void findUnitOfMeasureByDescription() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(DESCRIPTION);

        unitOfMeasureRectiveRepository.save(unitOfMeasure).block();

        UnitOfMeasure savedUOM = unitOfMeasureRectiveRepository.findUnitOfMeasureByDescription(DESCRIPTION).block();

        assertNotNull(savedUOM.getId());
        assertEquals(DESCRIPTION, savedUOM.getDescription());
    }
}