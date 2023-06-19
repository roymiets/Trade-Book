package com.cm.bbEquityapi.mapper;


import com.cm.bbEquityapi.dto.EquityDto;
import com.cm.bbEquityapi.enums.EquityStatus;
import com.cm.bbEquityapi.model.Equity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class EquityMapperTest {
    private EquityMapper equityMapper = Mappers.getMapper(EquityMapper.class);

    @Test
    void convertDtoToEntity_ValidEquityDto_ReturnsEquityEntity() {
        // Mock data
        EquityDto equityDto = new EquityDto();
        equityDto.setId(1L);
        equityDto.setName("Apple Inc.");
        equityDto.setSymbol("AAPL");
        equityDto.setMarket("NASDAQ");
        equityDto.setStatus(EquityStatus.ACTIVE);
        // Perform the mapping
        Equity equity = equityMapper.convertDtoToEntity(equityDto);
        // Verify the expected behavior
        assertEquals(equityDto.getId(), equity.getId());
        assertEquals(equityDto.getName(), equity.getName());
        assertEquals(equityDto.getSymbol(), equity.getSymbol());
        assertEquals(equityDto.getMarket(), equity.getMarket());
        assertEquals(equityDto.getStatus(), equity.getStatus());
    }

    @Test
    void convertEntityToDto_ValidEquityEntity_ReturnsEquityDto() {
        // Mock data
        Equity equity = new Equity();
        equity.setId(1L);
        equity.setName("Apple Inc.");
        equity.setSymbol("AAPL");
        equity.setMarket("NASDAQ");
        equity.setStatus(EquityStatus.ACTIVE);
        // Perform the mapping
        EquityDto equityDto = equityMapper.convertEntityToDto(equity);
        // Verify the expected behavior
        assertEquals(equity.getId(), equityDto.getId());
        assertEquals(equity.getName(), equityDto.getName());
        assertEquals(equity.getSymbol(), equityDto.getSymbol());
        assertEquals(equity.getMarket(), equityDto.getMarket());
        assertEquals(equity.getStatus(), equityDto.getStatus());
    }
}
