package com.taskmanager.task_manager.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for ModelMapper bean setup.
 * Provides a singleton instance of ModelMapper for object mapping throughout the application.
 */
@Configuration
public class ModelMapperConfig {
    
    /**
     * Creates and configures a ModelMapper instance for object-to-object mapping.
     * ModelMapper simplifies the conversion between entities and DTOs.
     *
     * @return a configured ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}