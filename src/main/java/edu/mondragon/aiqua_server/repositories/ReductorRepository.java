package edu.mondragon.aiqua_server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mondragon.aiqua_server.models.Reductor;
import edu.mondragon.aiqua_server.models.Town;

public interface ReductorRepository extends JpaRepository<Reductor, Integer>{

    List<Reductor> findByTown(Town town);
    
}
