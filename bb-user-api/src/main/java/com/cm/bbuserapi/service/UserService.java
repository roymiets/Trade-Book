package com.cm.bbuserapi.service;

import com.cm.bbuserapi.dto.GenericResponse;
import com.cm.bbuserapi.dto.UserDto;
import com.cm.bbuserapi.dto.UserRequestDto;
import com.cm.bbuserapi.enums.UserStatus;
import com.cm.bbuserapi.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    ResponseEntity<GenericResponse> register(UserDto userDto);
    ResponseEntity<GenericResponse> authenticate(UserRequestDto userRequestDto);
    //UserDto loadUserByUsername(String username) throws UsernameNotFoundException;
    ResponseEntity<GenericResponse> updateUserDetails(Long id, UserDto userDto);

    List<UserDto> searchUsers(String searchTerm);

    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void deleteUserById(Long id);
    void deleteAllUsers();
    void updateUserStatus(Long id, UserStatus status);
}
