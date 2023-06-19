package com.cm.bbuserapi.mapper;
import com.cm.bbuserapi.dto.UserDto;
import com.cm.bbuserapi.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void convertDtoToEntity_ValidUserDto_ReturnsUserEntity() {
        // Mock data
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        //userDto.setUserName("john.doe");
        userDto.setEmail("john.doe@example.com");

        // Perform the mapping
        User user = userMapper.convertDtoToEntity(userDto);

        // Verify the expected behavior
        assertEquals(userDto.getId(), user.getId());
        //assertEquals(userDto.getName(), user.getUserName());
        assertEquals(userDto.getEmail(), user.getEmail());
    }

    @Test
    void convertEntityToDto_ValidUserEntity_ReturnsUserDto() {
        // Mock data
        User user = new User();
        user.setId(1L);
        // user.setUserName("john.doe");
        user.setEmail("john.doe@example.com");

        // Perform the mapping
        UserDto userDto = userMapper.convertEntityToDto(user);

        // Verify the expected behavior
        assertEquals(user.getId(), userDto.getId());
        //assertEquals(user.getUserName(), userDto.getName());
        assertEquals(user.getEmail(), userDto.getEmail());
    }
}
