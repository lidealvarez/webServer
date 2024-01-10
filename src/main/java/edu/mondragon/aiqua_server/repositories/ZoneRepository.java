package edu.mondragon.aiqua_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mondragon.aiqua_server.models.Zone;

public interface ZoneRepository extends JpaRepository<Zone, Integer>{
    
}
