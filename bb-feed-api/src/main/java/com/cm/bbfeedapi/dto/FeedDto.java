package com.cm.bbfeedapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FeedDto {
    private Long id;
    private String feedContent;
    private Long userId;
    private Long equityId;
    private List<String> imageUrl;
    private List<String> videoUrl;
    private Long likeCount;
    private Long commentCount;
    private Long shareCount;
    private Date createDateTime;
    private Date updateDateTime;
}
