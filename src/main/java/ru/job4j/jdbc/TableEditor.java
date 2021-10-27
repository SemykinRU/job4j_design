package ru.job4j.jdbc;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("db.driver_class"));
        String url = properties.getProperty("db.url");
        String login = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) {
        try(Statement statement = connection.createStatement()) {
            String key = "id serial primary key";
            String sql = String.format("create table if not exists %s(%s)", tableName, key);
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try(Statement statement = connection.createStatement()) {
            String sql = String.format("drop table if exists %s", tableName);
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try(Statement statement = connection.createStatement()) {
            String sql = String.format("alter table if exists %s add column if not exists %s %s",
                                            tableName, columnName, type);
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try(Statement statement = connection.createStatement()) {
            String sql = String.format("alter table if exists %s drop column if exists %s",
                    tableName, columnName);
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try(Statement statement = connection.createStatement()) {
            String sql = String.format("alter table if exists %s rename column %s to %s",
                    tableName, columnName, newColumnName);
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader(".\\data\\idea_db_config.properties"));
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("test");
        tableEditor.addColumn("test", "name", "text");
        tableEditor.addColumn("test", "value", "int");
        tableEditor.addColumn("test", "name2", "varchar(255)");
        tableEditor.renameColumn("test", "name2", "newName");
        tableEditor.dropColumn("test", "newName");
        tableEditor.dropTable("test");
        tableEditor.close();
    }
}