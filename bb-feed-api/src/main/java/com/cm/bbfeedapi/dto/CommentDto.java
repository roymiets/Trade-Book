package com.cm.bbfeedapi.dto;

import com.cm.bbfeedapi.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class CommentDto {
    private Long id;
    private Long feedId;
    private Long userId;
    private Long likes;
    private List<Long> replyIds;
    private List<String> imageUrl;
    private List<String> videoUrl;
    private String commentContent;
    private Date createDateTime;
    private Date updateDateTime;
}
