package com.unisangil.backend.appsalud.modules.authModule.services.impl;

import com.unisangil.backend.appsalud.modules.authModule.persitence.entities.UserEntity;
import com.unisangil.backend.appsalud.modules.authModule.persitence.repositories.UserRepository;
import com.unisangil.backend.appsalud.modules.authModule.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
}
