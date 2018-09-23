package com.example.jpatests;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name="roles", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Role {

    public Role(){}
    
    public Role(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(length=30)
    private String name;

    @ManyToMany(mappedBy="roles")
    private Set<User> users = new HashSet<User>();    

}