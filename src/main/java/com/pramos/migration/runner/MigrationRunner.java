package com.pramos.migration.runner;

import com.pramos.migration.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MigrationRunner implements CommandLineRunner {

    @Autowired
    private MigrationService migrationService;

    @Override
    public void run(String... args) throws Exception {
        migrationService.applyMigrations();
    }
}
