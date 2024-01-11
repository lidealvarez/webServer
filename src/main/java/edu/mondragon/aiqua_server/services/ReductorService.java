package edu.mondragon.aiqua_server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.mondragon.aiqua_server.models.Reductor;
import edu.mondragon.aiqua_server.models.Town;
import edu.mondragon.aiqua_server.repositories.ReductorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReductorService {
    private ReductorRepository reductorRepository;

    public List<Reductor> list() {
        return reductorRepository.findAll();
    }

    public Reductor readReductor(Integer reductorID) {
        Optional<Reductor> reductors = reductorRepository.findById(reductorID);
        Reductor reductor;

        if (reductors.isPresent()) {
            reductor = reductors.get();
        } else {
            reductor = null;
        }

        return reductor;
    }

    public List<Reductor> readReductorsByTown(Town town) {
        List<Reductor> reductors = reductorRepository.findByTown(town);

        if (!reductors.isEmpty()) {
            return reductors;
        } else {
            return new ArrayList<>();
        }
    }

    public void deleteReductor(Integer id) {
        Reductor reductor = readReductor(id);
        reductorRepository.delete(reductor);
    }
}
