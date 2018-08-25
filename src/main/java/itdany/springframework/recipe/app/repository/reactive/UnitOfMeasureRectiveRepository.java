package itdany.springframework.recipe.app.repository.reactive;

import itdany.springframework.recipe.app.model.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UnitOfMeasureRectiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String> {
    Mono<UnitOfMeasure> findUnitOfMeasureByDescription(String description);
}
