package com.cm.bbuserapi.mapper;


import com.cm.bbuserapi.dto.UserDto;
import com.cm.bbuserapi.model.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {


    @Mapping(source = "username", target = "userName")
    User convertDtoToEntity(UserDto userDto);

    @Mapping(source = "userName", target = "userName")
    @Mapping(target = "authorities", ignore = true)
    UserDto convertEntityToDto(User user);


}
