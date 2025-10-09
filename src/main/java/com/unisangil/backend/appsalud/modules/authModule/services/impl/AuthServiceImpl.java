package com.unisangil.backend.appsalud.modules.authModule.services.impl;

import com.unisangil.backend.appsalud.modules.authModule.persitence.entities.UserEntity;
import com.unisangil.backend.appsalud.modules.authModule.persitence.repositories.UserRepository;
import com.unisangil.backend.appsalud.modules.authModule.services.IAuthService;
import com.unisangil.backend.appsalud.modules.authModule.services.IJWTUtilityService;
import com.unisangil.backend.appsalud.modules.authModule.services.models.dtos.LoginDTO;
import com.unisangil.backend.appsalud.modules.authModule.services.models.dtos.ResponseDTO;
import com.unisangil.backend.appsalud.modules.authModule.services.models.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IJWTUtilityService jwtUtiliyService;
    @Autowired
    private UserValidation userValidation;

    @Override
    public HashMap<String, String> login(LoginDTO login) throws Exception {
        try {
            HashMap<String, String> jwt = new HashMap<>();
            Optional<UserEntity> user = userRepository.findByEmail(login.getEmail());
            
                if (user.isEmpty()){
                    jwt.put("error", "Usuario no registrado!!!!");
                    return jwt;
                }

            if(verifiyPassword(login.getPassword(), user.get().getPassword())){
                jwt.put("jwt", jwtUtiliyService.generateJWT(user.get().getId()));
            } else {
                jwt.put("error", "Authentication fallida");
            }
            return jwt;
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    @Override
    public ResponseDTO register(UserEntity user) throws Exception {
        try {
            ResponseDTO response =  userValidation.validate(user);

            if (response.getNumOfErrors()>0){
                return response;
            }

            List<UserEntity> getAllUsers = userRepository.findAll();
            for (UserEntity existingUser : getAllUsers) {
                if (existingUser.getEmail().equals(user.getEmail())) {
                    response.setNumOfErrors(1);
                    response.setMsj("El correo ya existe!!!");
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            response.setMsj("Usuario creado con exito!!!");

            return response;

        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    private boolean verifiyPassword(String enteredPassword, String storedPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
