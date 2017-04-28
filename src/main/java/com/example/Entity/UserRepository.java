package com.example.Entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mateu on 28.04.2017 , 01:02.
 *
 * UserRepository for User entity manage
 */

public interface UserRepository extends CrudRepository<User,Long>{
    User findByName(String name);

    User findById(Long id);

}
