package edu.mondragon.aiqua_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mondragon.aiqua_server.models.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer>{
    
}
