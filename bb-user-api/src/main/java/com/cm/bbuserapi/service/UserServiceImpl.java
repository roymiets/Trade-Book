package com.cm.bbuserapi.service;

import com.cm.bbuserapi.customException.ResourceNotFoundException;
import com.cm.bbuserapi.dto.GenericResponse;
import com.cm.bbuserapi.dto.UserDto;
import com.cm.bbuserapi.dto.UserRequestDto;
import com.cm.bbuserapi.enums.UserStatus;
import com.cm.bbuserapi.mapper.UserMapper;
import com.cm.bbuserapi.model.User;
import com.cm.bbuserapi.repository.UserRepo;
import com.cm.tokenUtils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService ,UserService{


    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public ResponseEntity<GenericResponse> register(UserDto userDto) {
        ResponseEntity<GenericResponse> responseEntity;
        GenericResponse response;
        if (null == userDto.getPhone() || null == userDto.getPassword() || null == userDto.getName()) {
            response = new GenericResponse("Insufficient information", null);
            responseEntity = ResponseEntity.badRequest().body(response);
            return responseEntity;
        }

        User user = userRepo.save(userMapper.convertDtoToEntity(userDto));
        response = new GenericResponse("User registered successfully", userMapper.convertEntityToDto(user));

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.AUTHORIZATION,
                jwtUtil.generateToken(user.getUserName()));
        responseEntity = ResponseEntity.ok().headers(responseHeaders).body(response);
        return responseEntity;

    }

    public ResponseEntity<GenericResponse> authenticate(UserRequestDto userRequestDto) {
        ResponseEntity<GenericResponse> responseEntity;
        GenericResponse response;
        User user = userRepo.findUserByPhone(userRequestDto.getPhone()).orElse(null);
        if (null == user) {
            response = new GenericResponse("User Does not exist!", null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        if (user.getPassword().equals(userRequestDto.getPassword()) && user.getPhone().equals(userRequestDto.getPhone())) {
            response = new GenericResponse("User logged in successfully", userMapper.convertEntityToDto(user));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(HttpHeaders.AUTHORIZATION,
                    jwtUtil.generateToken(user.getUserName()));
            responseEntity = ResponseEntity.ok().headers(responseHeaders).body(response);
        } else {
            response = new GenericResponse("Credentials are invalid", null);
            responseEntity = ResponseEntity.badRequest().body(response);
        }
        return responseEntity;
    }


    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.convertEntityToDto(userRepo.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist")));
    }

    public ResponseEntity<GenericResponse> updateUserDetails(Long Id,UserDto userDto) {
        ResponseEntity<GenericResponse> responseEntity;
        User existingUser = userRepo.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + Id));

        User updatedUser = userRepo.save(userMapper.convertDtoToEntity(userDto));
        GenericResponse response = new GenericResponse("User details updated successfully", userMapper.convertEntityToDto(updatedUser));
        responseEntity = ResponseEntity.ok(response);
        return responseEntity;
    }

    @Override
    public List<UserDto> searchUsers(String searchTerm) {
        List<User> equities =userRepo.searchUsersByUserNameOrName("%" + searchTerm + "%");
        return equities.stream()
                .map(userMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }
    public  List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public void deleteUserById(Long id) {
        Optional<User> user=userRepo.findById(id);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        userRepo.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepo.deleteAll();
    }

    public void updateUserStatus(Long id, UserStatus status) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id));
        user.setStatus(status);
        userRepo.save(user);
    }
}
