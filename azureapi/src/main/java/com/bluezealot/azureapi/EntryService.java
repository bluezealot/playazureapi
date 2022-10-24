package com.bluezealot.azureapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EntryService implements CommandLineRunner, ExitCodeGenerator{

    @Autowired
    AppConfig config;

    @Override
    public int getExitCode() {
        log.info("Console End---");
        log.info("Config: " + config.getAuthUri());
        return 0;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Console Start---");
        
    }
    
}
