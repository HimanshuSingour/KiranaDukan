package com.v1.manage.v8.init.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {

    @Id
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "kirana_store_id")
    private KiranaStore kiranaStore;

    private Integer rating; // 1 to 5
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;

    // Getters and Setters
}
