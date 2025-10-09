package com.unisangil.backend.appsalud.modules.authModule.services;

import com.unisangil.backend.appsalud.modules.authModule.persitence.entities.UserEntity;

import java.util.List;

public interface IUserService {

    public List<UserEntity> findAllUsers();

}
