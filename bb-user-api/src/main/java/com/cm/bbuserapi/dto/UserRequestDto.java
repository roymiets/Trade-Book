package com.cm.bbuserapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class UserRequestDto implements Serializable{
    private  String phone;
    private  String password;
}
