package com.pramos.migration.core;

import com.pramos.migration.scripts.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScriptRegistry {

    public List<MigrationScript> getAllScripts() {
        return List.of(
            new DDL_08072024143215_CreateUsersTable(),
            new DDL_090720250425_CreateInitialTables()
        );
    }
}