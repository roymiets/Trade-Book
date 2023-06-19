package com.cm.bbfeedapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse {
    private String msg;
    private Object payload;
}
