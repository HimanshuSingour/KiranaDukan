package com.v1.manage.v8.init.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KiranaStore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ownerId;

    @Column(nullable = false, unique = true)
    private String storeName;

    private String address;

    private Boolean isVerified;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private KiranaUser kiranaUser;
}
