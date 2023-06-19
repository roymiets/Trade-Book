package com.cm.bbfeedapi.service;

import com.cm.bbfeedapi.dto.GenericResponse;
import com.cm.bbfeedapi.dto.ReactionDto;
import com.cm.bbfeedapi.mapper.ReactionMapper;
import com.cm.bbfeedapi.model.Reaction;
import com.cm.bbfeedapi.repository.ReactionRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReactionServiceImpl implements ReactionService{

    private final ReactionRepo reactionRepo;

    private  final ReactionMapper reactionMapper;

    @Override
    public ResponseEntity<GenericResponse> saveReaction(ReactionDto reactionDto) {
        if(reactionDto==null){
            return ResponseEntity.badRequest().body(new GenericResponse("invalid request ",null));
        }
        Reaction reaction=reactionMapper.convertDtoToEntity(reactionDto);
        reaction=reactionRepo.save(reaction);
        ReactionDto savedReactionDto=reactionMapper.convertEntityToDto(reaction);
        GenericResponse response=new GenericResponse("Reaction save successfully",savedReactionDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GenericResponse> getReactionById(Long id) {
        if(id==null){
            return  ResponseEntity.badRequest().body(new GenericResponse("invalid request",null));
        }
        Reaction  reaction = reactionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reaction  not found"));
        ReactionDto reactionDto=reactionMapper.convertEntityToDto(reaction);
        GenericResponse response=new GenericResponse("Reaction retrieved successfully",reactionDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GenericResponse> getAllReactions() {
        List<Reaction> reactions=reactionRepo.findAll();
        List<ReactionDto> reactionDtos=reactions.stream()
                .map(reactionMapper::convertEntityToDto)
                .collect(Collectors.toList());
        GenericResponse response=new GenericResponse("Reactions retrieved successfully",reactionDtos);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GenericResponse> deleteReactionById(Long id) {
        if (id==null){
            return ResponseEntity.badRequest().body(new GenericResponse("invalid request",null));
        }
        reactionRepo.deleteById(id);
        GenericResponse response=new GenericResponse("Reaction deleted successfully",null);
        return ResponseEntity.ok(response);
    }

}
