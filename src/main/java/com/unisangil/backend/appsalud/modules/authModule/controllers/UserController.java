package com.unisangil.backend.appsalud.modules.authModule.controllers;

import com.unisangil.backend.appsalud.modules.authModule.persitence.entities.UserEntity;
import com.unisangil.backend.appsalud.modules.authModule.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/all")
    private ResponseEntity<List<UserEntity>> obtenerTodos(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}
