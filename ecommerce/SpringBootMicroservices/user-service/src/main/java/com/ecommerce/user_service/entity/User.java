package com.ecommerce.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="user_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends Details {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Role> role;

}
