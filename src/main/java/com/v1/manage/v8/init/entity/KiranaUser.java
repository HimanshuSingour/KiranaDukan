package com.v1.manage.v8.init.entity;

import com.v1.manage.v8.init.utils.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Component
public class KiranaUser {

    @Id
    private String userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String otp;
    private boolean isVerified;

    @Column(nullable = false, unique = true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "kiranaUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<KiranaStore> kiranaStores = new HashSet<>();

}
