package itdany.springframework.recipe.app.repository.reactive;

import itdany.springframework.recipe.app.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {
    Mono<Category> findCategoryByDescription(String description);
}
