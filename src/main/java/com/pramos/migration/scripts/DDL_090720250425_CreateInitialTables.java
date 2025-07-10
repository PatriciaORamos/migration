package com.pramos.migration.scripts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.pramos.migration.core.MigrationScript;

public class DDL_090720250425_CreateInitialTables implements MigrationScript {

    @Override
    public String getId() {
        return "090720250425";
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS logins (
                    id SERIAL PRIMARY KEY,
                    user_id INT UNIQUE NOT NULL REFERENCES users(id),
                    email VARCHAR(255) UNIQUE NOT NULL,
                    password TEXT NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS locations (
                    id SERIAL PRIMARY KEY,
                    user_id INT NOT NULL REFERENCES users(id),
                    province CHAR(2) NOT NULL,
                    city VARCHAR(100) NOT NULL,
                    hour_rate NUMERIC(5,2) NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS builds (
                    id SERIAL PRIMARY KEY,
                    user_id INT NOT NULL REFERENCES users(id),
                    name VARCHAR(100) NOT NULL,
                    location_id INT NOT NULL REFERENCES locations(id)
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS work_schedules (
                    id SERIAL PRIMARY KEY,
                    build_id INT NOT NULL REFERENCES builds(id),
                    days_of_week VARCHAR(50) NOT NULL,
                    start_time TIME NOT NULL,
                    end_time TIME NOT NULL,
                    hours_per_day NUMERIC(4,2) NOT NULL,
                    holiday BOOLEAN DEFAULT FALSE,
                    weight NUMERIC(3,2) DEFAULT 1.0
                )
            """);
        }
    }
}
