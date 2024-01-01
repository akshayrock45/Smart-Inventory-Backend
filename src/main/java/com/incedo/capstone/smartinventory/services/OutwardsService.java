package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.dto.OutwardsDTO;
import com.incedo.capstone.smartinventory.entities.Outwards;
import com.incedo.capstone.smartinventory.exceptions.OutwardsCreationException;
import com.incedo.capstone.smartinventory.exceptions.OutwardsNotFoundException;
import com.incedo.capstone.smartinventory.mapper.OutwardsMapper;
import com.incedo.capstone.smartinventory.repository.OutwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OutwardsService {

    @Autowired
    OutwardsRepository outwardsRepository;

    public String addOutwards(Outwards outwards) {

        Outwards savedOutwards = outwardsRepository.save(outwards);

        if(savedOutwards != null)
        {
            return "Added Outwards";
        }
        else {
            throw new OutwardsCreationException("There is Some Problem Creating the Outwards");
        }
    }

    public OutwardsDTO updateById(long outwardsId, OutwardsDTO outwardsDto) {

        Optional<Outwards> op = outwardsRepository.findById(outwardsId);

        if(op.isPresent())
        {
            Outwards existingOutwards = op.get();

            existingOutwards.setBillCheckedBy(outwardsDto.getBillCheckedBy());
            existingOutwards.setBillValue(outwardsDto.getBillValue());
            existingOutwards.setDateOfDelivery(outwardsDto.getDateOfDelivery());
            existingOutwards.setInvoiceNo(outwardsDto.getInvoiceNo());
            existingOutwards.setReceiptNo(outwardsDto.getReceiptNo());
            existingOutwards.setPurpose(outwardsDto.getPurpose());
            existingOutwards.setItemName(outwardsDto.getItemName());
            existingOutwards.setDeliveredTo(outwardsDto.getDeliveredTo());
            existingOutwards.setQuantity(outwardsDto.getQuantity());
            existingOutwards.setDateOfSupply(outwardsDto.getDateOfSupply());

            Outwards savedOutwards = outwardsRepository.save(existingOutwards);

            return OutwardsMapper.convertToDto(savedOutwards);


        }
        else {
            throw new OutwardsNotFoundException("Outwards not found with id: " + outwardsId);
        }
    }

    public List<OutwardsDTO> fetchOutwards() {

        return outwardsRepository.findAll()
                .stream()
                .map(OutwardsMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public OutwardsDTO fetchById(long outwardsId) {

        Optional<Outwards> op = outwardsRepository.findById(outwardsId);

        if(op.isPresent())
        {
            Outwards existingOutwards = op.get();

            return OutwardsMapper.convertToDto(existingOutwards);

        }
        throw new OutwardsNotFoundException("No record found for id: " + outwardsId);
    }

    public String deleteById(long outwardsId) {

        Optional<Outwards> op = outwardsRepository.findById(outwardsId);

        if(op.isPresent())
        {
            Outwards existingOutwards = op.get();

            outwardsRepository.delete(existingOutwards);
            return " Outwards deleetd Successfully";
        }
        throw new OutwardsNotFoundException("No records found for id: " + outwardsId);
    }
}
