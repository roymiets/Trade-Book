package com.cm.bbEquityapi.mapper;



import com.cm.bbEquityapi.dto.EquityDto;
import com.cm.bbEquityapi.model.Equity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface EquityMapper {
     Equity convertDtoToEntity(EquityDto equityDto);
     EquityDto convertEntityToDto(Equity equity);


}
