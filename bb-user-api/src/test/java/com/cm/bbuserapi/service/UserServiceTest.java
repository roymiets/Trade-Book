package com.cm.bbuserapi.service;

import com.cm.bbuserapi.dto.GenericResponse;
import com.cm.bbuserapi.dto.UserDto;
import com.cm.bbuserapi.dto.UserRequestDto;
import com.cm.bbuserapi.mapper.UserMapper;
import com.cm.bbuserapi.model.User;
import com.cm.bbuserapi.repository.UserRepo;
import com.cm.tokenUtils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private UserMapper userMapper;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void testRegister_Successful() {
        UserDto userDto = createUserDto();
        User user = createUser(userDto);

        when(userMapper.convertDtoToEntity(userDto)).thenReturn(user);
        when(userRepo.save(user)).thenReturn(user);
        when(userMapper.convertEntityToDto(user)).thenReturn(userDto);
        when(jwtUtil.generateToken(user.getUserName())).thenReturn("dummy_token");

        ResponseEntity<GenericResponse> response = userService.register(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody().getMsg());
        assertEquals(userDto, response.getBody().getPayload());
        assertEquals("dummy_token", response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

        verify(userRepo, times(1)).save(user);
        verify(userMapper, times(1)).convertDtoToEntity(userDto);
        verify(userMapper, times(1)).convertEntityToDto(user);
        verify(jwtUtil, times(1)).generateToken(user.getUserName());
        verifyNoMoreInteractions(userRepo, userMapper, jwtUtil);
    }

    @Test
    public void testRegister_InsufficientInformation() {
        UserDto userDto = new UserDto();

        ResponseEntity<GenericResponse> response = userService.register(userDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Insufficient information", response.getBody().getMsg());
        assertEquals(null, response.getBody().getPayload());

        verifyNoInteractions(userRepo, userMapper, jwtUtil);
    }

    @Test
    public void testAuthenticate_UserExists_Successful() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setPhone("1234567890");
        userRequestDto.setPassword("password");

        User user = createUser(userRequestDto);

        when(userRepo.findUserByPhone(userRequestDto.getPhone())).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken(user.getUserName())).thenReturn("dummy_token");

        ResponseEntity<GenericResponse> response = userService.authenticate(userRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User logged in successfully", response.getBody().getMsg());
        assertEquals(userMapper.convertEntityToDto(user), response.getBody().getPayload());
        assertEquals("dummy_token", response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

        verify(userRepo, times(1)).findUserByPhone(userRequestDto.getPhone());
        verify(jwtUtil, times(1)).generateToken(user.getUserName());
        verifyNoMoreInteractions(userRepo, jwtUtil);
    }

    @Test
    public void testAuthenticate_UserDoesNotExist() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setPhone("1234567890");
        userRequestDto.setPassword("password");

        when(userRepo.findUserByPhone(userRequestDto.getPhone())).thenReturn(Optional.empty());

        ResponseEntity<GenericResponse> response = userService.authenticate(userRequestDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User Does not exist!", response.getBody().getMsg());
        assertEquals(null, response.getBody().getPayload());

        verify(userRepo, times(1)).findUserByPhone(userRequestDto.getPhone());
        verifyNoMoreInteractions(userRepo, jwtUtil);
    }


//    @Test
//    public void testAuthenticate_InvalidCredentials() {
//        UserRequestDto userRequestDto = new UserRequestDto();
//        userRequestDto.setPhone("1234567890");
//        userRequestDto.setPassword("incorrect_password");
//
//        User user = createUser(userRequestDto);
//
//        when(userRepo.findUserByPhone(userRequestDto.getPhone())).thenReturn(Optional.of(user));
//
//        ResponseEntity<GenericResponse> response = userService.authenticate(userRequestDto);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Credentials are invalid", response.getBody().getMsg());
//        assertEquals(null, response.getBody().getPayload());
//
//        verify(userRepo, times(1)).findUserByPhone(userRequestDto.getPhone());
//        verifyNoMoreInteractions(userRepo, jwtUtil);
//    }
//
//    @Test
//    public void testLoadUserByUsername_UserExists_Successful() {
//        String username = "john123";
//        User user = createUser(username);
//
//        when(userRepo.findUserByUserName(username)).thenReturn(Optional.of(user));
//        when(userMapper.convertEntityToDto(user)).thenReturn(createUserDto());
//
//        UserDto result = userService.loadUserByUsername(username);
//
//        assertEquals(createUserDto(), result);
//
//        verify(userRepo, times(1)).findUserByUserName(username);
//        verify(userMapper, times(1)).convertEntityToDto(user);
//        verifyNoMoreInteractions(userRepo, userMapper);
//    }

    @Test
    public void testLoadUserByUsername_UserDoesNotExist() {
        String username = "john123";

        when(userRepo.findUserByUserName(username)).thenReturn(Optional.empty());

        try {
            userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException ex) {
            assertEquals("User does not exist", ex.getMessage());
        }

        verify(userRepo, times(1)).findUserByUserName(username);
        verifyNoMoreInteractions(userRepo, userMapper);
    }

    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John");
        userDto.setPhone("1234567890");
        userDto.setPassword("password");
        return userDto;
    }

    private User createUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        return user;
    }

    private User createUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setPhone(userRequestDto.getPhone());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }

    private User createUser(String username) {
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setPhone("1234567890");
        user.setPassword("password");
        user.setUserName(username);
        return user;
    }
}
