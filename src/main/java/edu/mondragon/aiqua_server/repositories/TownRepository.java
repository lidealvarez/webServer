package edu.mondragon.aiqua_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mondragon.aiqua_server.models.Town;
import edu.mondragon.aiqua_server.models.Zone;

public interface TownRepository extends JpaRepository<Town, Integer>{
    List<Town> findByZone(Zone zone);
    Town findByDescription(String description);
}
