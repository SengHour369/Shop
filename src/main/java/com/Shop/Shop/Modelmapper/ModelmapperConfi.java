package com.Shop.Shop.Modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelmapperConfi {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
