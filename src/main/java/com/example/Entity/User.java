package com.example.Entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by mateu on 28.04.2017 , 00:49.
 *
 * Entity class for Users table
 */
@Data
@Entity
@Table(name = "Users")
public class User {
    // TODO
    // unique a name property
    // this solution doesn't work for me

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name" , unique = true)
    private String name;

    @NotNull
    private String password;

    public User() {}

    public User(Long id){
        this.id = id;
    }

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
}
