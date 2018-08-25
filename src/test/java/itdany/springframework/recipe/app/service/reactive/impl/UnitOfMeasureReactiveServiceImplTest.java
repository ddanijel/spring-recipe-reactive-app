package itdany.springframework.recipe.app.service.reactive.impl;

import itdany.springframework.recipe.app.converter.modeltodto.UnitOfMeasureModelToUnitOfMeasureDTO;
import itdany.springframework.recipe.app.dto.UnitOfMeasureDTO;
import itdany.springframework.recipe.app.model.UnitOfMeasure;
import itdany.springframework.recipe.app.repository.reactive.UnitOfMeasureRectiveRepository;
import itdany.springframework.recipe.app.service.reactive.UnitOfMeasureReactiveService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UnitOfMeasureReactiveServiceImplTest {

    UnitOfMeasureModelToUnitOfMeasureDTO toUnitOfMeasureDTO = new UnitOfMeasureModelToUnitOfMeasureDTO();
    UnitOfMeasureReactiveService unitOfMeasureReactiveService;

    @Mock
    UnitOfMeasureRectiveRepository unitOfMeasureRectiveRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        unitOfMeasureReactiveService = new UnitOfMeasureReactiveServiceImpl(
                unitOfMeasureRectiveRepository, toUnitOfMeasureDTO);
    }

    @Test
    public void getAllUnitOfMeasuresDTO() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1");
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId("2");
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRectiveRepository.findAll()).thenReturn(Flux.just(uom1, uom2));

        List<UnitOfMeasureDTO> dtos = unitOfMeasureReactiveService.getAllUnitOfMeasuresDTO().collectList().block();

        assertEquals(2, dtos.size());
        verify(unitOfMeasureRectiveRepository, times(1)).findAll();
    }
}