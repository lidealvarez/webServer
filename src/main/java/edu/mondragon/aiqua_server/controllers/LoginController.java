package edu.mondragon.aiqua_server.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mondragon.aiqua_server.models.User;
import edu.mondragon.aiqua_server.models.UserType;
import edu.mondragon.aiqua_server.services.UserService;
import edu.mondragon.aiqua_server.services.UserTypeService;
import edu.mondragon.aiqua_server.services.ZoneService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    UserService userService;

    UserTypeService userTypeService;

    ZoneService zoneService;

    @PostMapping(value = "/userLogin", produces = { "application/json", "application/xml" }, consumes = {
            "application/json", "application/xml" })
    public ResponseEntity<User> userLogin(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        User foundUser = userService.readUser(username);
        if (foundUser != null) {
            if (password.equals(foundUser.getPassword())) {
                return new ResponseEntity<>(foundUser, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/userRegister", produces = { "application/json", "application/xml" }, consumes = {
            "application/json", "application/xml" })
    public ResponseEntity<User> userRegister(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();
        String surname = user.getSurname();
        String phone = user.getPhone();
        String email = user.getEmail();
        UserType type = user.getUserType();

        User createdUser = userService.createUser(username, password, name, surname, phone, email, type,
                user.getZones());

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

}
