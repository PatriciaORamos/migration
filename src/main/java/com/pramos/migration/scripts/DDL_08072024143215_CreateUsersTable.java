package com.pramos.migration.scripts;

import com.pramos.migration.core.MigrationScript;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DDL_08072024143215_CreateUsersTable implements MigrationScript {

    @Override
    public String getId() {
        return "08072024143215";
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                first_name VARCHAR(100) NOT NULL,
                last_name VARCHAR(100) NOT NULL
            )
        """;
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
}
