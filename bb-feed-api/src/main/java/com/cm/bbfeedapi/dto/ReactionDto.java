package com.cm.bbfeedapi.dto;

import com.cm.bbfeedapi.enums.ReactionType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReactionDto {
    private Long id;
    private Long feedId;
    private Long commentId;
    private Long  userId;
    private ReactionType type;
    private Date createDateTime;
    private Date updateDateTime;
}
