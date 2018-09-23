package com.example.jpatests;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class User {

    public User(){}
    public User(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(length=30)
    private String name;

    @Column 
    private LocalDateTime createdAt;


    @ManyToMany
    @JoinTable(name = "users_roles", 
            joinColumns = { @JoinColumn(name="user_id") } , 
            inverseJoinColumns = { @JoinColumn(name="role_id") } 
    )
    private Set<Role> roles = new HashSet<Role>();

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

}