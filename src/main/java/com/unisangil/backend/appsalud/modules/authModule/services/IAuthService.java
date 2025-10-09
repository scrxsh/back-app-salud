package com.unisangil.backend.appsalud.modules.authModule.services;

import com.unisangil.backend.appsalud.modules.authModule.persitence.entities.UserEntity;
import com.unisangil.backend.appsalud.modules.authModule.services.models.dtos.LoginDTO;
import com.unisangil.backend.appsalud.modules.authModule.services.models.dtos.ResponseDTO;

import java.util.HashMap;

public interface IAuthService {

    public HashMap<String, String> login(LoginDTO login) throws Exception;
    public ResponseDTO register(UserEntity user) throws  Exception;
}
