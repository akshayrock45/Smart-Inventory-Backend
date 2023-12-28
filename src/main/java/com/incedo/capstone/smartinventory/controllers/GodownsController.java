package com.incedo.capstone.smartinventory.controllers;

import com.incedo.capstone.smartinventory.services.GodownsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GodownsController {

    @Autowired
    GodownsService godownsService;
}
