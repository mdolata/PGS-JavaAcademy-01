package com.example.Entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mateu on 28.04.2017 , 00:49.
 *
 * Entity class for Users table
 */
@Data
@ToString(exclude = "password")
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // has unique constraint in db
    @NotNull
    @Column(updatable = false)
    private String name;

    @NotNull
    private String password;

    private String email;

    @Column(updatable = false)
    private Date insertDate;

    private Date updateDate;

    public User() {}

    public User(Long id){
        this.id = id;
    }

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
}
