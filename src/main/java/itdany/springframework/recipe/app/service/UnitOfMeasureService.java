package itdany.springframework.recipe.app.service;

import itdany.springframework.recipe.app.dto.UnitOfMeasureDTO;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureDTO> getAllUnitOfMeasuresDTO();
}
