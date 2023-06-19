package com.cm.bbuserapi.controller;

import com.cm.bbuserapi.customException.ResourceNotFoundException;
import com.cm.bbuserapi.dto.GenericResponse;
import com.cm.bbuserapi.dto.UserDto;
import com.cm.bbuserapi.dto.UserRequestDto;
import com.cm.bbuserapi.enums.UserStatus;
import com.cm.bbuserapi.model.User;
import com.cm.bbuserapi.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final  UserServiceImpl userService;
    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Heart beating");
    }

    @PostMapping(value = "/register")
    public ResponseEntity<GenericResponse> doRegister(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<GenericResponse> doLogin(@RequestBody UserRequestDto userRequestDto) {
        return userService.authenticate(userRequestDto);
    }

    @GetMapping("/search")
    public ResponseEntity<GenericResponse> searchUsers(@RequestParam(value = "term") String searchTerm) {
        List<UserDto> searchResults =userService.searchUsers(searchTerm);
        GenericResponse response = new GenericResponse("Users search ", searchResults);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{Id}")
    public ResponseEntity<GenericResponse> updateUserDetails( @PathVariable Long Id,@RequestBody UserDto userDto) {
        return userService.updateUserDetails(Id,userDto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found");
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        User user = optionalUser.get();
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        userService.deleteUserById(id);
        return ResponseEntity.ok("User with ID " + id + " has been deleted");
    }
    @DeleteMapping("delete/all")
    public ResponseEntity<String> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok("All users have been deleted");
    }
    @PatchMapping("update/{id}")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long id, @RequestParam("status") UserStatus status) {
        userService.updateUserStatus(id, status);
        String message = "User with ID " + id + " has been " + status.name().toLowerCase();
        return ResponseEntity.ok(message);
    }
}
