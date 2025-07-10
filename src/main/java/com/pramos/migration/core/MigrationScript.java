package com.pramos.migration.core;

import java.sql.Connection;
import java.sql.SQLException;

public interface MigrationScript {
    String getId();
    void execute(Connection connection) throws SQLException;
}