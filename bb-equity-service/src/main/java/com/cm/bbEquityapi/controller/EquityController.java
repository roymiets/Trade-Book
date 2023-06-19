package com.cm.bbEquityapi.controller;

import com.cm.bbEquityapi.dto.EquityDto;
import com.cm.bbEquityapi.dto.GenericResponse;
import com.cm.bbEquityapi.enums.EquityStatus;
import com.cm.bbEquityapi.exception.ResourceNotFoundException;
import com.cm.bbEquityapi.model.Equity;
import com.cm.bbEquityapi.service.EquityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equity")
@RequiredArgsConstructor
public class EquityController {

    private final EquityServiceImpl equityService;
    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Heart beating");
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse> createEquity(@RequestBody EquityDto equityDto) {
        return equityService.createEquity(equityDto);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<GenericResponse> saveAllEquities(@RequestBody List<EquityDto> equityDtoList) {
        return equityService.saveAllEquities(equityDtoList);
    }
    @GetMapping("/search")
    public ResponseEntity<GenericResponse> searchEquities(@RequestParam(value = "term") String searchTerm) {
        List<EquityDto> searchResults = equityService.searchEquities(searchTerm);
        GenericResponse response = new GenericResponse("Equities search results", searchResults);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<GenericResponse> updateEquityDetails(@PathVariable Long Id, @RequestBody EquityDto equityDto) {
        return equityService.updateEquityDetails(Id,equityDto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Equity>> getAllEquity(@RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy) {
        List<Equity> users = equityService.getAllEquities(pageNo,pageSize,sortBy);
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No equity found");
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equity> getEquityById(@PathVariable Long id) {
        Optional<Equity> optionalUser = equityService.getEquityById(id);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("equity not found with id: " + id);
        }
        Equity equity = optionalUser.get();
        return ResponseEntity.ok(equity);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEquityById(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
      equityService.deleteEquityById(id);
        return ResponseEntity.ok("equity with ID " + id + " has been deleted");
    }
    @DeleteMapping("delete/all")
    public ResponseEntity<String> deleteAllEquity() {
       equityService.deleteAllEquities();
        return ResponseEntity.ok("All equity have been deleted");
    }
    @PatchMapping("update/{id}")
    public ResponseEntity<String> updateEquityStatus(@PathVariable Long id, @RequestParam("status") EquityStatus status) {
       equityService.updateEquityStatus(id, status);
        String message = "equity with ID " + id + " has been " + status.name().toLowerCase();
        return ResponseEntity.ok(message);
    }
}
