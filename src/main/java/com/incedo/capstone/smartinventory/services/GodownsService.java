package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.repository.GodownsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GodownsService {

    @Autowired
    GodownsRepository godownsRepository;
}
