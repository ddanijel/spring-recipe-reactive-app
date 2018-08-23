package itdany.springframework.recipe.app.repository;

import itdany.springframework.recipe.app.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, String> {
    Optional<UnitOfMeasure> findUnitOfMeasureByDescription(String description);
}
