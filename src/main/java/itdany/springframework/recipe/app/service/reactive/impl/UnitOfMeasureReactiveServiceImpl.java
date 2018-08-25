package itdany.springframework.recipe.app.service.reactive.impl;

import itdany.springframework.recipe.app.converter.modeltodto.UnitOfMeasureModelToUnitOfMeasureDTO;
import itdany.springframework.recipe.app.dto.UnitOfMeasureDTO;
import itdany.springframework.recipe.app.repository.reactive.UnitOfMeasureRectiveRepository;
import itdany.springframework.recipe.app.service.reactive.UnitOfMeasureReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class UnitOfMeasureReactiveServiceImpl implements UnitOfMeasureReactiveService {

    private final UnitOfMeasureRectiveRepository unitOfMeasureRectiveRepository;
    private final UnitOfMeasureModelToUnitOfMeasureDTO toUnitOfMeasureDTO;

    public UnitOfMeasureReactiveServiceImpl(UnitOfMeasureRectiveRepository unitOfMeasureRectiveRepository,
                                            UnitOfMeasureModelToUnitOfMeasureDTO toUnitOfMeasureDTO) {
        this.unitOfMeasureRectiveRepository = unitOfMeasureRectiveRepository;
        this.toUnitOfMeasureDTO = toUnitOfMeasureDTO;
    }

    @Override
    public Flux<UnitOfMeasureDTO> getAllUnitOfMeasuresDTO() {
        return unitOfMeasureRectiveRepository
                .findAll()
                .map(toUnitOfMeasureDTO::convert);
    }
}
