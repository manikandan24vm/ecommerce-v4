package com.ecommerce.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long roleId;
    private String description;
    @Enumerated(EnumType.STRING)  // Store Enum as String in the DB
    private RoleName roleNames;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    public enum RoleName{
        ADMIN,CUSTOMER
    }
}
