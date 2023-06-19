package com.cm.bbfeedapi.controller;

import com.cm.bbfeedapi.dto.GenericResponse;
import com.cm.bbfeedapi.dto.ReactionDto;
import com.cm.bbfeedapi.service.ReactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/reaction")
@RestController
public class ReactionController {
    private final ReactionServiceImpl reactionService;
    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Heart beating");
    }
    @PostMapping("/create")
    public ResponseEntity<GenericResponse> createReaction(@RequestBody ReactionDto reactionDto) {
        ResponseEntity<GenericResponse> createdReaction =reactionService.saveReaction(reactionDto);
        GenericResponse response = new GenericResponse("Reaction created successfully", createdReaction);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> getReactionById(@PathVariable("id") Long id) {
        ResponseEntity<GenericResponse> reaction =reactionService.getReactionById(id);
        GenericResponse response = new GenericResponse("Reaction retrieved successfully", reaction);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse> getAllReactions() {
        ResponseEntity<GenericResponse> reactions = reactionService.getAllReactions();
        GenericResponse response = new GenericResponse("Reactions retrieved successfully",reactions);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteReactionById(@PathVariable("id") Long id) {
        reactionService.deleteReactionById(id);
        GenericResponse response = new GenericResponse("Reaction deleted successfully", null);
        return ResponseEntity.ok(response);
    }


}
