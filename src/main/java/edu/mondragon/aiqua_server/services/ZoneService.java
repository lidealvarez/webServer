package edu.mondragon.aiqua_server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.mondragon.aiqua_server.models.Zone;
import edu.mondragon.aiqua_server.repositories.ZoneRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ZoneService {
    private ZoneRepository zoneRepository;


    public List<Zone> list() {
        return zoneRepository.findAll();
    }

    public Zone readZone(Integer zoneID) {
        Optional<Zone> zones = zoneRepository.findById(zoneID);
        Zone zone;

        if (zones.isPresent()) {
            zone = zones.get();
        } else {
            zone = null;
        }

        return zone;
    }

    public void createZone(String description) {
        Zone zone = new Zone();
        zone.setDescription(description);
        zoneRepository.save(zone);
    }

    public void updateZone(Integer zoneID, String description) {
        Zone zone = readZone(zoneID);
        zone.setDescription(description);
        zoneRepository.save(zone);
    }

    public void deleteZone(Integer id) {
        Zone zone = readZone(id);
        zoneRepository.delete(zone);
    }
}
