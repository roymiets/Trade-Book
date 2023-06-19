package com.cm.bbfeedapi.controller;

import com.cm.bbfeedapi.dto.CommentDto;
import com.cm.bbfeedapi.dto.GenericResponse;
import com.cm.bbfeedapi.service.CommentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentServiceImpl commentService;

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Heart beating");
    }
    @PostMapping("/create")
    public ResponseEntity<GenericResponse> createComment(@RequestBody CommentDto commentDto) {
        ResponseEntity<GenericResponse> createdComment = commentService.saveComment(commentDto);
        GenericResponse response = new GenericResponse("Comment created successfully", createdComment);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> getCommentById(@PathVariable("id") Long id) {
        ResponseEntity<GenericResponse> comment = commentService.getCommentById(id);
        GenericResponse response = new GenericResponse("Comment retrieved successfully", comment);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse> getAllComments() {
        ResponseEntity<GenericResponse> comments = commentService.getAllComments();
        GenericResponse response = new GenericResponse("Comments retrieved successfully", comments);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteCommentById(@PathVariable("id") Long id) {
        commentService.deleteCommentById(id);
        GenericResponse response = new GenericResponse("Comment deleted successfully", null);
        return ResponseEntity.ok(response);
    }

}
