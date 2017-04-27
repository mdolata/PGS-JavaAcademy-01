package com.example.Entity;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by mateu on 28.04.2017 , 01:02.
 *
 * UserDao for User entity manage
 */

public interface UserDao extends CrudRepository<User,Long>{
    User findByName(String name);
}
