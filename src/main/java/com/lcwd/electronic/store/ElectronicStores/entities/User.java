package com.lcwd.electronic.store.ElectronicStores.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_ID")
    private String userId;
    @Column(name="user_name")
    private String name;
    @Column(name="user_email", unique = true)
    private String email;
    @Column(name="user_password",length = 500)
    private String password;
    private String gender;
    @Column(length = 1000)
    private String about;
    @Column(name="user_image_name")
    private String imageName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<Role> roles = new HashSet<>();

}
