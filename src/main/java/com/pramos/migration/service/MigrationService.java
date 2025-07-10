package com.pramos.migration.service;

import com.pramos.migration.core.MigrationScript;
import com.pramos.migration.core.ScriptRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class MigrationService {

    @Autowired
    private ScriptRegistry scriptRegistry;

    @Autowired
    private DataSource dataSource;

    public void applyMigrations() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            createMigrationTableIfNotExists(connection);

            for (MigrationScript script : scriptRegistry.getAllScripts()) {
                if (!isScriptAlreadyApplied(connection, script.getId())) {
                    script.execute(connection);
                    markScriptAsApplied(connection, script.getId());
                }
            }
        }
    }

    private void createMigrationTableIfNotExists(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS migration (
                id VARCHAR(20) PRIMARY KEY
            )
        """;
        conn.createStatement().execute(sql);
    }

    private boolean isScriptAlreadyApplied(Connection conn, String id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM migration WHERE id = ?");
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }

    private void markScriptAsApplied(Connection conn, String id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO migration (id) VALUES (?)");
        stmt.setString(1, id);
        stmt.executeUpdate();
    }
}
