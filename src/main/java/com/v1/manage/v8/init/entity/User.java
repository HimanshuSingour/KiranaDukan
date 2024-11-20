package com.v1.manage.v8.init.entity;

import com.v1.manage.v8.init.constant.UserRole;
import com.v1.manage.v8.init.entity.KiranaStore;
import com.v1.manage.v8.init.entity.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    // Relationships
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private KiranaStore kiranaStore; // Link to KiranaStore if the user is an OWNER

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>(); // Link to orders for CUSTOMERS

    public User(String username, String password, String email, Set<UserRole> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

}
