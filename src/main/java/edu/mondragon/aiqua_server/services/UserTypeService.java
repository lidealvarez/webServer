package edu.mondragon.aiqua_server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.mondragon.aiqua_server.models.UserType;
import edu.mondragon.aiqua_server.repositories.UserTypeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserTypeService {
    private UserTypeRepository userTypeRepository;


    public List<UserType> list() {
        return userTypeRepository.findAll();
    }

    public void createType(String name) {
        UserType type = new UserType();
        type.setDescription(name);
        userTypeRepository.save(type);
    }

    public UserType readType(Integer userTypeID) {
        Optional<UserType> types = userTypeRepository.findById(userTypeID);
        UserType type;
        if(types.isPresent()){
            type = types.get();
        }else{
            type = null;
        }
        return type;
    }

    public void updateType(Integer userTypeID, String name) {
        UserType type = readType(userTypeID);
        type.setDescription(name);
        userTypeRepository.save(type);
    }

    public void deleteType(Integer id){
        UserType type = readType(id);
        userTypeRepository.delete(type);
    }
}
