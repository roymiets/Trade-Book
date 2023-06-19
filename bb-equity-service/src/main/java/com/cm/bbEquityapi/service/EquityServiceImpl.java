package com.cm.bbEquityapi.service;


import com.cm.bbEquityapi.dto.EquityDto;
import com.cm.bbEquityapi.dto.GenericResponse;
import com.cm.bbEquityapi.enums.EquityStatus;
import com.cm.bbEquityapi.exception.ResourceNotFoundException;
import com.cm.bbEquityapi.mapper.EquityMapper;
import com.cm.bbEquityapi.model.Equity;
import com.cm.bbEquityapi.repository.EquityRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquityServiceImpl implements  EquityService{

    private final EquityRepo equityRepository;

    private  final  EquityMapper equityMapper;

    public ResponseEntity<GenericResponse> createEquity(EquityDto equityDto) {
        // Convert EquityDto to Equity entity

        // Save the Equity entity in the database
        equityRepository.save(equityMapper.convertDtoToEntity(equityDto));

        // Return a GenericResponse with a success message
        GenericResponse response = new GenericResponse("Equity created successfully",equityMapper.convertDtoToEntity(equityDto));
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public ResponseEntity<GenericResponse> saveAllEquities(List<EquityDto> equityDtoList) {
        if (equityDtoList == null || equityDtoList.isEmpty()) {
            return ResponseEntity.badRequest().body(new GenericResponse("Invalid request", null));
        }

        List<Equity> equities = equityDtoList.stream()
                .map(equityMapper::convertDtoToEntity)
                .collect(Collectors.toList());

        List<Equity> savedEquities = equityRepository.saveAll(equities);

        List<EquityDto> savedEquityDtos = savedEquities.stream()
                .map(equityMapper::convertEntityToDto)
                .collect(Collectors.toList());

        GenericResponse response = new GenericResponse("Equities saved successfully", savedEquityDtos);
        return ResponseEntity.ok(response);
    }
    @Override
    public List<EquityDto> searchEquities(String searchTerm) {
        List<Equity> equities = equityRepository.searchEquitiesBySymbolOrName("%" + searchTerm + "%");
        return equities.stream()
                .map(equityMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<GenericResponse> updateEquityDetails(Long id, EquityDto equityDto) {
        // Check if an Equity entity exists with the given ID
        Optional<Equity> optionalEquity = equityRepository.findById(id);
        if (optionalEquity.isEmpty()) {
            throw new ResourceNotFoundException("Equity not found with id: " + id);
        }

        // Update the Equity entity with the new data
        Equity equity = optionalEquity.get();
        equity.setName(equityDto.getName());
        equity.setStatus(equityDto.getStatus());
        equity.setSymbol(equityDto.getSymbol());
        equity.setMarket(equityDto.getMarket());
        equityRepository.save(equity);

        // Return a GenericResponse with a success message
        GenericResponse response = new GenericResponse("Equity updated successfully",equity);
        return ResponseEntity.ok(response);
    }

    public List<Equity> getAllEquities(Integer pageNo,Integer pageSize,String sortBy) {
        // Retrieve all Equity entities from the database
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Equity> pagedResult = equityRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Equity>();
        }

    }

    public Optional<Equity> getEquityById(Long id) {
        // Retrieve the Equity entity with the given ID from the database
        return equityRepository.findById(id);
    }

    public ResponseEntity<GenericResponse> deleteEquityById(Long id) {
        // Delete the Equity entity with the given ID from the database
        equityRepository.deleteById(id);
        return null;
    }

    public void deleteAllEquities() {
        // Delete all Equity entities from the database
        equityRepository.deleteAll();
    }

    public void updateEquityStatus(Long id, EquityStatus status) {
        // Check if an Equity entity exists with the given ID
        Optional<Equity> optionalEquity = equityRepository.findById(id);
        if (optionalEquity.isEmpty()) {
            throw new ResourceNotFoundException("Equity not found with id: " + id);
        }

        // Update the Equity entity with the new status
        Equity equity = optionalEquity.get();
        equity.setStatus(status);
        equityRepository.save(equity);
    }
}
