package com.cm.bbfeedapi.service;

import com.cm.bbfeedapi.dto.CommentDto;
import com.cm.bbfeedapi.dto.GenericResponse;
import com.cm.bbfeedapi.mapper.CommentMapper;
import com.cm.bbfeedapi.model.Comment;
import com.cm.bbfeedapi.repository.CommentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @InjectMocks
    private CommentServiceImpl commentService;
    @Mock
    private CommentRepo commentRepository;
    @Mock
    private CommentMapper commentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentService = new CommentServiceImpl(commentRepository, commentMapper);
    }

    @Test
    void saveComment_ValidRequest_ReturnsResponseEntityWithCommentDto() {
        // Mock data
        CommentDto commentDto = new CommentDto();
        Comment comment = new Comment();
        CommentDto savedCommentDto = new CommentDto();
        savedCommentDto.setId(1L);

        // Mock repository and mapper behavior
        when(commentMapper.convertDtoToEntity(commentDto)).thenReturn(comment);
        when(commentRepository.save(comment)).thenReturn(comment);
        when(commentMapper.convertEntityToDto(comment)).thenReturn(savedCommentDto);

        // Perform the service method
        ResponseEntity<GenericResponse> response = commentService.saveComment(commentDto);

        // Verify the expected behavior
        assertEquals("Comment created successfully", response.getBody().getMsg());
        assertEquals(savedCommentDto, response.getBody().getPayload());

        verify(commentMapper, times(1)).convertDtoToEntity(commentDto);
        verify(commentRepository, times(1)).save(comment);
        verify(commentMapper, times(1)).convertEntityToDto(comment);
    }

    @Test
    void getCommentById_ValidId_ReturnsResponseEntityWithCommentDto() {
        // Mock data
        Long commentId = 1L;
        Comment comment = new Comment();
        CommentDto commentDto = new CommentDto();

        // Mock repository and mapper behavior
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(commentMapper.convertEntityToDto(comment)).thenReturn(commentDto);

        // Perform the service method
        ResponseEntity<GenericResponse> response = commentService.getCommentById(commentId);

        // Verify the expected behavior
        assertEquals("Comment retrieved successfully", response.getBody().getMsg());
        assertEquals(commentDto, response.getBody().getPayload());

        verify(commentRepository, times(1)).findById(commentId);
        verify(commentMapper, times(1)).convertEntityToDto(comment);
    }

    @Test
    void getAllComments_ReturnsResponseEntityWithListOfCommentDto() {
        // Mock data
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        List<Comment> comments = Arrays.asList(comment1, comment2);
        CommentDto commentDto1 = new CommentDto();
        CommentDto commentDto2 = new CommentDto();
        List<CommentDto> commentDtos = Arrays.asList(commentDto1, commentDto2);

        // Mock repository and mapper behavior
        when(commentRepository.findAll()).thenReturn(comments);
        when(commentMapper.convertEntityToDto(comment1)).thenReturn(commentDto1);
        when(commentMapper.convertEntityToDto(comment2)).thenReturn(commentDto2);

        // Perform the service method
        ResponseEntity<GenericResponse> response = commentService.getAllComments();

        // Verify the expected behavior
        assertEquals("Comments retrieved successfully", response.getBody().getMsg());
        assertEquals(commentDtos, response.getBody().getPayload());

        verify(commentRepository, times(1)).findAll();
        verify(commentMapper, times(1)).convertEntityToDto(comment1);
        verify(commentMapper, times(1)).convertEntityToDto(comment2);
    }
    @Test
    void deleteCommentById_ValidId_ReturnsResponseEntityWithSuccessMessage() {
        // Mock data
        Long commentId = 1L;

        // Perform the service method
        ResponseEntity<GenericResponse> response = commentService.deleteCommentById(commentId);

        // Verify the expected behavior
        assertEquals("Comment deleted successfully", response.getBody().getMsg());
        assertEquals(null, response.getBody().getPayload());

        verify(commentRepository, times(1)).deleteById(commentId);
    }


}
