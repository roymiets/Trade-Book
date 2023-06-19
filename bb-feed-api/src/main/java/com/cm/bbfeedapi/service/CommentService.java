package com.cm.bbfeedapi.service;

import com.cm.bbfeedapi.dto.CommentDto;
import com.cm.bbfeedapi.dto.GenericResponse;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<GenericResponse> saveComment(CommentDto commentDto);
    ResponseEntity<GenericResponse> getCommentById(Long id);
    ResponseEntity<GenericResponse> getAllComments();
    ResponseEntity<GenericResponse> deleteCommentById(Long id);
}
