package edu.mondragon.aiqua_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mondragon.aiqua_server.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    List<User> findByUsername(String username);

    List<User> findByUsernameAndPassword(String username, String password);
    
}
