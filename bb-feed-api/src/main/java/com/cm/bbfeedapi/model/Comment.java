package com.cm.bbfeedapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long feedId;
    private Long userId;
    private Long likes;
    private List<Long> replyIds;
    private List<String> imageUrl;
    private List<String> videoUrl;
    @Lob
    private String commentContent;
    @CreationTimestamp
    private Date createDateTime;
    @UpdateTimestamp
    private Date updateDateTime;
}
