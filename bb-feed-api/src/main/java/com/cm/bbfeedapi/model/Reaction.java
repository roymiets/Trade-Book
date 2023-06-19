package com.cm.bbfeedapi.model;

import com.cm.bbfeedapi.enums.ReactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long feedId;
    private Long commentId;
    private Long  userId;
    @Enumerated(EnumType.STRING)
    private ReactionType type;
    @CreationTimestamp
    private Date createDateTime;
    @UpdateTimestamp
    private Date updateDateTime;
}
