package com.cm.bbfeedapi.service;

import com.cm.bbfeedapi.dto.CommentDto;
import com.cm.bbfeedapi.dto.GenericResponse;
import com.cm.bbfeedapi.mapper.CommentMapper;
import com.cm.bbfeedapi.model.Comment;
import com.cm.bbfeedapi.repository.CommentRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepository;
    private final CommentMapper commentMapper;


    public ResponseEntity<GenericResponse> saveComment(CommentDto commentDto) {
        if (commentDto == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Invalid request", null));
        }
        Comment comment = commentMapper.convertDtoToEntity(commentDto);
        comment = commentRepository.save(comment);
        CommentDto savedCommentDto = commentMapper.convertEntityToDto(comment);
        GenericResponse response = new GenericResponse("Comment created successfully", savedCommentDto);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<GenericResponse> getCommentById(Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Invalid request", null));
        }
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        CommentDto commentDto = commentMapper.convertEntityToDto(comment);
        GenericResponse response = new GenericResponse("Comment retrieved successfully", commentDto);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<GenericResponse> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentDtos = comments.stream()
                .map(commentMapper::convertEntityToDto)
                .collect(Collectors.toList());
        GenericResponse response = new GenericResponse("Comments retrieved successfully", commentDtos);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<GenericResponse> deleteCommentById(Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Invalid request", null));
        }
        commentRepository.deleteById(id);
        GenericResponse response = new GenericResponse("Comment deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
