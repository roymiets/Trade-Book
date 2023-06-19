package com.cm.bbEquityapi.Service;

import com.cm.bbEquityapi.dto.EquityDto;
import com.cm.bbEquityapi.dto.GenericResponse;
import com.cm.bbEquityapi.enums.EquityStatus;
import com.cm.bbEquityapi.mapper.EquityMapper;
import com.cm.bbEquityapi.model.Equity;
import com.cm.bbEquityapi.repository.EquityRepo;
import com.cm.bbEquityapi.service.EquityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EquityServiceTest {
    @Mock
    private EquityRepo equityRepository;

    @Mock
    private EquityMapper equityMapper;
    @InjectMocks
    private EquityServiceImpl equityService;

    @Test
    void updateEquityStatus_ShouldUpdateEquityStatusIfExists() {
        Long id = 1L;
        EquityStatus newStatus = EquityStatus.INACTIVE;

        Equity existingEquity = new Equity();
        existingEquity.setId(id);
        existingEquity.setName("Apple Inc.");
        existingEquity.setSymbol("AAPL");
        existingEquity.setMarket("NASDAQ");
        existingEquity.setStatus(EquityStatus.ACTIVE);
        existingEquity.setCreateDateTime(new Date());
        existingEquity.setUpdateDateTime(new Date());

        when(equityRepository.findById(id)).thenReturn(Optional.of(existingEquity));
        when(equityRepository.save(any(Equity.class))).thenReturn(existingEquity);

        assertDoesNotThrow(() -> equityService.updateEquityStatus(id, newStatus));

        assertEquals(newStatus, existingEquity.getStatus());

        verify(equityRepository, times(1)).findById(id);
        verify(equityRepository, times(1)).save(any(Equity.class));
    }
    @Test
    void getEquityById_ShouldReturnEquityIfExists() {
        Long id = 1L;

        Equity equity = new Equity();
        equity.setId(id);
        equity.setName("Apple Inc.");
        equity.setSymbol("AAPL");
        equity.setMarket("NASDAQ");
        equity.setStatus(EquityStatus.ACTIVE);
        equity.setCreateDateTime(new Date());
        equity.setUpdateDateTime(new Date());

        when(equityRepository.findById(id)).thenReturn(Optional.of(equity));

        Optional<Equity> result = equityService.getEquityById(id);

        assertTrue(result.isPresent());
        assertEquals(equity, result.get());

        verify(equityRepository, times(1)).findById(id);
    }

    @Test
    void getEquityById_ShouldReturnEmptyOptionalIfNotExists() {
        Long id = 1L;

        when(equityRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Equity> result = equityService.getEquityById(id);

        assertFalse(result.isPresent());

        verify(equityRepository, times(1)).findById(id);
    }

    @Test
    void deleteEquityById_ShouldDeleteEquityIfExists() {
        Long id = 1L;

        doNothing().when(equityRepository).deleteById(id);

        assertDoesNotThrow(() -> equityService.deleteEquityById(id));

        verify(equityRepository, times(1)).deleteById(id);
    }


    @Test
    void deleteAllEquities_ShouldDeleteAllEquities() {
        doNothing().when(equityRepository).deleteAll();

        assertDoesNotThrow(() -> equityService.deleteAllEquities());

        verify(equityRepository, times(1)).deleteAll();
    }

}
