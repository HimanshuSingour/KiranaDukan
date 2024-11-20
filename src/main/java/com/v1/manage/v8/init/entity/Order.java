package com.v1.manage.v8.init.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String status; // PENDING, CONFIRMED, DELIVERED

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Customer who placed the order

    @ManyToOne
    @JoinColumn(name = "kirana_store_id", nullable = false)
    private KiranaStore kiranaStore; // Store fulfilling the order

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems = new HashSet<>();

    // Getters and Setters
}
