package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.dto.GodownsDTO;
import com.incedo.capstone.smartinventory.entities.Godowns;
import com.incedo.capstone.smartinventory.exceptions.GodownCreationException;
import com.incedo.capstone.smartinventory.exceptions.GodownNotFoundException;
import com.incedo.capstone.smartinventory.mapper.GodownsMapper;
import com.incedo.capstone.smartinventory.repository.GodownsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GodownsService {

    @Autowired
    GodownsRepository godownsRepository;

    public String addGodown(Godowns godown) {
        if (godown.getLocation() == null || godown.getLocation().isEmpty()) {
            throw new GodownCreationException("Location cannot be null for a Godown");
        }
        Godowns existingGodown = godownsRepository.findByLocation(godown.getLocation());

        if (existingGodown != null && existingGodown.getLocation().equals(godown.getLocation())) {
            throw new GodownCreationException("Godown already Exist!");
        } else {
            Godowns savedGodown = godownsRepository.save(godown);
            if (savedGodown != null) {
                return "Godown Added";
            }
            throw new GodownCreationException("There is some problem creating godown");
        }
    }

    public GodownsDTO updateGodown(long godownId, GodownsDTO godownsDto) {
        Optional<Godowns> op = godownsRepository.findById(godownId);

        if (op.isPresent()) {
            Godowns existingGodown = op.get();
            existingGodown.setLocation(godownsDto.getLocation());
            existingGodown.setCapacityInQuintals(godownsDto.getCapacityInQuintals());

            Godowns uodatedGodown = godownsRepository.save(existingGodown);

            return GodownsMapper.convertToDto(uodatedGodown);
        } else {
            throw new GodownNotFoundException("Godown not found with id: " + godownId);
        }
    }

    public List<GodownsDTO> fetchGodowns() {
        return godownsRepository.findAll()
                .stream()
                .map(GodownsMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public List<GodownsDTO> fetchByLocation(String location) {
        List<Godowns> godowns = godownsRepository.findBylocationContaining(location);

        if (godowns.isEmpty()) {
            throw new GodownNotFoundException("No records found for location: " + location);
        }

        return godowns.stream()
                .map(GodownsMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public GodownsDTO fetchById(long godownId) {

        Optional<Godowns> op = godownsRepository.findById(godownId);

        if(op.isPresent())
        {
            Godowns existingGodown = op.get();

            return GodownsMapper.convertToDto(existingGodown);
        }
        throw new GodownNotFoundException("no records found for: " + godownId);
    }

    public String deleteById(long godownId) {

        Optional<Godowns> op = godownsRepository.findById(godownId);

        if(op.isPresent())
        {
            Godowns existinggodown  = op.get();
            godownsRepository.delete(existinggodown);
            return "Godown Deleted Successfully";
        }
        else
            throw new GodownNotFoundException("Godown Not found for id: " + godownId);

    }

    public List<Godowns> fetchGodowns2() {
        return godownsRepository.findAll();
    }
}
