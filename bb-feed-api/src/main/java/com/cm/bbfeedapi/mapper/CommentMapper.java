package com.cm.bbfeedapi.mapper;

import com.cm.bbfeedapi.dto.CommentDto;
import com.cm.bbfeedapi.model.Comment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface CommentMapper {
    Comment convertDtoToEntity(CommentDto commentDto);

    CommentDto convertEntityToDto(Comment comment);

}
