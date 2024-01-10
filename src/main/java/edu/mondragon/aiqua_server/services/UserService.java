package edu.mondragon.aiqua_server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mondragon.aiqua_server.models.User;
import edu.mondragon.aiqua_server.models.UserType;
import edu.mondragon.aiqua_server.models.Zone;
import edu.mondragon.aiqua_server.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService() {
    }

    

    public List<User> list() {
        return userRepository.findAll();
    }

    public User readUser(String username, String password) {
        List<User> users = userRepository.findByUsernameAndPassword(username, password);
        User user;

        if (users.isEmpty()) {
            user = null;
        } else {
            user = users.get(0);
        }
        return user;
    }

    public List<Zone> readUserZones(Integer userID){
        User user = readUser(userID);
        List<Zone> zones = user.getZones();
        if (zones.isEmpty()) {
            zones = null;
        }
        return zones;
    }

    public User readUser(String username) {
        List<User> users = userRepository.findByUsername(username);
        User user;

        if (users.size() <= 0) {
            user = null;
        } else {
            user = users.get(0);
        }
        return user;
    }

    public User readUser(Integer userId) {
        Optional<User> users = userRepository.findById(userId);
        User user;

        if (users.isPresent()) {
            user = users.get();
        } else {
            user = null;
        }

        return user;
    }

    public User createUser(String username, String password, String name, String surname, String phone,
            String email, UserType type, List<Zone> zones) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setPhone(phone);
        user.setEmail(email);
        user.setUserType(type);
        user.setZones(zones);
        userRepository.save(user);
        return user;
    }

    public void updateUser(Integer userID, String username, String password, String name, String surname, String phone,
            String email, UserType type) {
        User user = readUser(userID);
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setPhone(phone);
        user.setEmail(email);
        user.setUserType(type);
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        User user = readUser(id);
        userRepository.delete(user);
    }
}
