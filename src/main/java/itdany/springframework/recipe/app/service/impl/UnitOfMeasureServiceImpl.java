package itdany.springframework.recipe.app.service.impl;

import itdany.springframework.recipe.app.converter.modeltodto.UnitOfMeasureModelToUnitOfMeasureDTO;
import itdany.springframework.recipe.app.dto.UnitOfMeasureDTO;
import itdany.springframework.recipe.app.repository.UnitOfMeasureRepository;
import itdany.springframework.recipe.app.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureModelToUnitOfMeasureDTO toUnitOfMeasureDTOConverter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureModelToUnitOfMeasureDTO toUnitOfMeasureDTOConverter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.toUnitOfMeasureDTOConverter = toUnitOfMeasureDTOConverter;
    }

    @Override
    public Set<UnitOfMeasureDTO> getAllUnitOfMeasuresDTO() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(toUnitOfMeasureDTOConverter::convert)
                .collect(Collectors.toSet());
    }
}
