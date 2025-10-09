package com.unisangil.backend.appsalud.modules.authModule.config;

import com.unisangil.backend.appsalud.modules.authModule.services.models.validation.UserValidation;
import org.springframework.context.annotation.Bean;

public class ValidationConfig {

    @Bean
    public UserValidation userValidation(){
        return new UserValidation();
    }
}
