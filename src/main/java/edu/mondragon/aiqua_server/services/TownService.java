package edu.mondragon.aiqua_server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.mondragon.aiqua_server.models.Town;
import edu.mondragon.aiqua_server.models.Zone;
import edu.mondragon.aiqua_server.repositories.TownRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TownService {
    private TownRepository townRepository;

    public List<Town> list() {
        return townRepository.findAll();
    }

    public Town readTown(Integer townID) {
        Optional<Town> towns = townRepository.findById(townID);
        Town town;

        if (towns.isPresent()) {
            town = towns.get();
        } else {
            town = null;
        }

        return town;
    }

    public List<Town> readTownsByZone(Integer zoneID) {
        Zone zone = new Zone();
        zone.setZoneID(zoneID);

        List<Town> towns = townRepository.findByZone(zone);

        if (!towns.isEmpty()) {
            return towns;
        } else {
            return new ArrayList<>();
        }
    }

    public Town readTownByDescription(String description){
        return townRepository.findByDescription(description);
    }

    public void createTown(String description) {
        Town town = new Town();
        town.setDescription(description);
        townRepository.save(town);
    }

    public void updateTown(Integer townID, String description) {
        Town town = readTown(townID);
        town.setDescription(description);
        townRepository.save(town);
    }

    public void deleteTown(Integer id) {
        Town town = readTown(id);
        townRepository.delete(town);
    }

}
