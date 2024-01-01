package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.dto.InwardsDTO;
import com.incedo.capstone.smartinventory.entities.Inwards;
import com.incedo.capstone.smartinventory.exceptions.InwardsCreationException;
import com.incedo.capstone.smartinventory.exceptions.InwardsNotFoundException;
import com.incedo.capstone.smartinventory.mapper.InwardsMapper;
import com.incedo.capstone.smartinventory.repository.InwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InwardsService {

    @Autowired
    InwardsRepository inwardsRepository;

    public String addInwards(Inwards inwards) {

        Inwards savedInwards = inwardsRepository.save(inwards);

        if (savedInwards != null) {
            return "Inwards Added";
        }
        throw new InwardsCreationException("There is some problem creating Inwards");
    }

    public InwardsDTO updateInwards(long inwardsId, InwardsDTO inwardsDto) {

        Optional<Inwards> op = inwardsRepository.findById(inwardsId);

        if (op.isPresent()) {
            Inwards existinginwards = op.get();

            existinginwards.setQuantity(inwardsDto.getQuantity());
            existinginwards.setBillCheckedBy(inwardsDto.getBillCheckedBy());
            existinginwards.setDateOfSupply(inwardsDto.getDateOfSupply());
            existinginwards.setInvoiceNo(inwardsDto.getInvoiceNo());
            existinginwards.setItemName(inwardsDto.getItemName());
            existinginwards.setReceiptNo(inwardsDto.getReceiptNo());
            existinginwards.setNameOfTheSupplier(inwardsDto.getNameOfTheSupplier());
            existinginwards.setReceivedBy(inwardsDto.getReceivedBy());

            Inwards savedInwards = inwardsRepository.save(existinginwards);

            return InwardsMapper.convetToDto(savedInwards);
        } else {
            throw new InwardsNotFoundException("Inwards not found for id: " + inwardsId);
        }
    }

    public List<InwardsDTO> fetchInwards() {

        return inwardsRepository.findAll()
                .stream()
                .map(InwardsMapper::convetToDto)
                .collect(Collectors.toList());
    }

    public InwardsDTO fetchInwardsById(long inwardsId) {

        Optional<Inwards> op = inwardsRepository.findById(inwardsId);

        if (op.isPresent()) {
            Inwards existingInwards = op.get();

            return InwardsMapper.convetToDto(existingInwards);
        }
        throw new InwardsNotFoundException("Inwards not found for id: " + inwardsId);
    }

    public String deleteById(long inwardsId) {

        Optional<Inwards> op = inwardsRepository.findById(inwardsId);

        if (op.isPresent()) {
            Inwards deleteInwards = op.get();
            if (deleteInwards != null) {
                inwardsRepository.delete(deleteInwards);
            }

        }
        throw new InwardsNotFoundException("Inwards not found for id: " + inwardsId);
    }
}
