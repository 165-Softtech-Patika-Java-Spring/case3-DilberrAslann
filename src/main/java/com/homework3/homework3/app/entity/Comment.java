package com.homework3.homework3.app.entity;

import com.homework3.homework3.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
@Getter
@Setter
public class Comment extends BaseEntity {
    @Id
    @SequenceGenerator(name = "Comment" , sequenceName = "COMMENT_ID_SEQ")
    @GeneratedValue(generator = "Comment")
    private Long id;

    @Column(name = "COMMENTARY", length = 100, nullable = false)
    private String commentary;

    @Column(name = "ID_USER")
    private Long userId;

    @Column(name = "ID_PRODUCT")
    private Long productId;


}
