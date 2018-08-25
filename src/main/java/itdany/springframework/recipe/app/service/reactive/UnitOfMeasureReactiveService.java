package itdany.springframework.recipe.app.service.reactive;

import itdany.springframework.recipe.app.dto.UnitOfMeasureDTO;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureReactiveService {
    Flux<UnitOfMeasureDTO> getAllUnitOfMeasuresDTO();
}
