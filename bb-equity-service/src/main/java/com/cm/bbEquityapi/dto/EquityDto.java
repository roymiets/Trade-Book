package com.cm.bbEquityapi.dto;

import com.cm.bbEquityapi.enums.EquityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquityDto {
    private Long id;
    private String name;
    private String symbol;
    private String market;
    private EquityStatus status;

}
