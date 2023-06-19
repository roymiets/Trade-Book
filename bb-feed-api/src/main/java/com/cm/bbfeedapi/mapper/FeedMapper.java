package com.cm.bbfeedapi.mapper;

import com.cm.bbfeedapi.dto.FeedDto;
import com.cm.bbfeedapi.model.Feed;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FeedMapper {
    Feed convertDtoToEntity(FeedDto feedDto);

    FeedDto convertEntityToDto(Feed feed);
}
