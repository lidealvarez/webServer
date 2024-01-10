package edu.mondragon.aiqua_server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.mondragon.aiqua_server.models.Reductor;
import edu.mondragon.aiqua_server.models.Town;
import edu.mondragon.aiqua_server.models.Zone;
import edu.mondragon.aiqua_server.services.ReductorService;
import edu.mondragon.aiqua_server.services.TownService;
import edu.mondragon.aiqua_server.services.UserService;
import edu.mondragon.aiqua_server.services.ZoneService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    ZoneService zoneService;

    @Autowired
    TownService townService;

    @Autowired
    UserService userService;

    @Autowired
    ReductorService reductorService;

    @PostMapping(value = "/zones", produces = { "application/json", "application/xml" }, consumes = {
            "application/json", "application/xml" })
    public ResponseEntity<List<Zone>> getZones(@RequestBody int userID) {
        List<Zone> zoneList = userService.readUserZones(userID);
        if (zoneList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(zoneList, HttpStatus.OK);
        }

    }

    @PostMapping(value = "/zoneTowns", produces = { "application/json", "application/xml" }, consumes = {
            "application/json", "application/xml" })
    public ResponseEntity<List<Town>> getTownsByZone(@RequestBody int zoneID) {
        List<Town> towns = townService.readTownsByZone(zoneID);

        if (towns.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(towns, HttpStatus.OK);
        }

    }

    @PostMapping(value = "/reductorsTown", produces = { "application/json", "application/xml" }, consumes = {
            "application/json", "application/xml" })
    public ResponseEntity<List<Reductor>> getReductorsByTown(@RequestBody String townDescription) {
        Town town = townService.readTownByDescription(townDescription);
        List<Reductor> reductors = reductorService.readReductorsByTown(town);

        if (reductors.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(reductors, HttpStatus.OK);
        }

    }

}
