package com.cm.bbfeedapi.service;

import com.cm.bbfeedapi.dto.GenericResponse;
import com.cm.bbfeedapi.dto.ReactionDto;
import org.springframework.http.ResponseEntity;

public interface ReactionService {
    ResponseEntity<GenericResponse> saveReaction(ReactionDto reactionDto);
    ResponseEntity<GenericResponse> getReactionById(Long id);
    ResponseEntity<GenericResponse> getAllReactions();
    ResponseEntity<GenericResponse> deleteReactionById(Long id);
}
