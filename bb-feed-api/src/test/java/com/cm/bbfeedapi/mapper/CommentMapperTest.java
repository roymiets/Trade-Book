package com.cm.bbfeedapi.mapper;

import com.cm.bbfeedapi.dto.CommentDto;
import com.cm.bbfeedapi.model.Comment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CommentMapperTest {
    private CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);

    @Test
    void convertDtoToEntity_ValidCommentDto_ReturnsCommentEntity() {
        // Mock data
        CommentDto commentDto = new CommentDto();
        commentDto.setId(1L);
        commentDto.setCommentContent("This is a comment");

        // Perform the mapping
        Comment comment = commentMapper.convertDtoToEntity(commentDto);

        // Verify the expected behavior
        assertEquals(commentDto.getId(), comment.getId());
        assertEquals(commentDto.getCommentContent(), comment.getCommentContent());
    }

    @Test
    void convertEntityToDto_ValidCommentEntity_ReturnsCommentDto() {
        // Mock data
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setCommentContent("This is a comment");

        // Perform the mapping
        CommentDto commentDto = commentMapper.convertEntityToDto(comment);

        // Verify the expected behavior
        assertEquals(comment.getId(), commentDto.getId());
        assertEquals(comment.getCommentContent(), commentDto.getCommentContent());
    }
}
