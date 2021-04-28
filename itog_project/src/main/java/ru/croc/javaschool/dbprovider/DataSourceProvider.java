package ru.croc.javaschool.dbprovider;

import org.apache.derby.jdbc.EmbeddedDataSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Провайдер для БД
 */
public class DataSourceProvider {

    /**
     * Data source.
     */
    private EmbeddedDataSource dataSource;

    /**
     * Параметры конфигурации.
     */
    private Map<String, String> properties = new HashMap<>();

    public DataSourceProvider() throws IOException {
        loadProperties();
    }

    /**
     * Загружает property из application.properties
     *
     * @throws IOException
     */
    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                this.properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            System.out.println("Error occurred during loading properties");
            throw e;
        }
    }

    /**
     * Получить доступ к БД, авториазия в БД через application.properties
     *
     * @return датасорс
     */
    public EmbeddedDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new EmbeddedDataSource();
            dataSource.setDatabaseName(properties.get("database.name"));
            String username = properties.get("database.username");
            String password = properties.get("database.password");
            if (username != null && !username.isEmpty()
                    && password != null && !password.isEmpty()) {
                dataSource.setUser(username);
                dataSource.setPassword(password);
            }
            dataSource.setCreateDatabase("create");
        }
        return dataSource;
    }
}


