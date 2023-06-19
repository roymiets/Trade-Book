package com.cm.bbfeedapi.mapper;

import com.cm.bbfeedapi.dto.ReactionDto;
import com.cm.bbfeedapi.model.Reaction;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ReactionMapper {
    Reaction convertDtoToEntity(ReactionDto reactionDto);

    ReactionDto convertEntityToDto(Reaction reaction);
}
