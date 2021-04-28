package ru.croc.javaschool.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.w3c.dom.ls.LSOutput;
import ru.croc.javaschool.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий людей.
 */
public class PersonRepository {
    /**
     * Имя таблицы.
     */
    private static final String TABLE_NAME = "person";
    /**
     * Хранилище.
     */
    private EmbeddedDataSource dataSource;

    public PersonRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Метод инициализации БД.
     */
    private void initTable() {
        System.out.println(
                String.format("Start initializing %s table",
                        TABLE_NAME));
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Table has already been initialized");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "id_person INTEGER PRIMARY KEY " +
                                "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                                + "name VARCHAR(255),"
                                + "second_name VARCHAR(255),"
                                + "third_name VARCHAR(255)"
                                + ")");
                System.out.println("Table was successfully initialized");

            }
        } catch (SQLException e) {
            System.out.println("Error occurred during table initializing: " + e.getMessage());
        } finally {
            System.out.println("=========================");
        }
    }

    /**
     * Метод поиска всех пациентов (людей).
     *
     * @return список всех пациентов
     */
    public List<Person> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM " + TABLE_NAME);
            List<Person> personList = new ArrayList<>();
            while (resultSet.next()) {
                personList.add(
                        new Person(
                                resultSet.getString("name"),
                                resultSet.getString("second_name"),
                                resultSet.getString("third_name"),
                                resultSet.getInt("id_person")
                        )
                );
            }
            return personList;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();

    }

    /**
     * Метод создания записи в БД о новом пациенте.
     *
     * @param person Пациент
     */
    public Person createNew(Person person) throws Exception {
        if (findInfoByFio(person.getName(), person.getSecondName(), person.getThirdName()) != null) {
            throw new Exception("Такой пациент уже есть");
        }
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " (name, second_name, third_name) " +
                "VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, person.getName());
            statement.setString(2, person.getSecondName());
            statement.setString(3, person.getThirdName());
            statement.execute();
            System.out.println("Запись добавлена");
            System.out.println("=========================");
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return findInfoByFio(person.getName(), person.getSecondName(), person.getThirdName());
    }

    /**
     * Получить пациента по id.
     *
     * @param id id пациента
     * @return пациент
     */
    public Person findInfoById(int id) {
        String sqlQuery = "SELECT name, second_name, third_name, id_person  " +
                "FROM " + TABLE_NAME +
                " WHERE id_person=?";
        Person person = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            person = new Person(
                    rs.getString("name"),
                    rs.getString("second_name"),
                    rs.getString("third_name"),
                    rs.getInt("id_person")
            );

        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса " + e.getMessage());
        }
        return person;
    }

    /**
     * Получить пациента по фио.
     *
     * @param name       имя пациента
     * @param secondName фамилия пациента
     * @param thirdName  отчество пациента
     * @return пациент
     */
    public Person findInfoByFio(String name, String secondName, String thirdName) {
        String sqlQuery = "SELECT name, second_name, third_name, id_person  " +
                "FROM " + TABLE_NAME +
                " WHERE " +
                "name=? AND " +
                "second_name=? AND " +
                "third_name=?";
        Person person = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, name);
            statement.setString(2, secondName);
            statement.setString(3, thirdName);
            ResultSet rs = statement.executeQuery();
            rs.next();
            person = new Person(
                    rs.getString("name"),
                    rs.getString("second_name"),
                    rs.getString("third_name"),
                    rs.getInt("id_person")
            );

        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса " + e.getMessage());
        }
        return person;
    }

    /**
     * Удалить человека по fio.
     *
     * @param name       имя пациента.
     * @param secondName фамилия пациента.
     * @param thirdName  отчество пациента.
     * @return состояние успешности удаления
     */
    public boolean deletePersonByFio(String name, String secondName, String thirdName) {
        if (findInfoByFio(name, secondName, thirdName) == null) {
            System.out.println(String.format("Нет пациента %s %s %s", name, secondName, thirdName));
            return false;
        }
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE name=? AND second_name=? AND third_name=?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, name);
            statement.setString(2, secondName);
            statement.setString(3, thirdName);
            statement.execute();
            System.out.println("Запись удалена");
            System.out.println("=========================");
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса " + e.getMessage());
        }
        return findInfoByFio(name, secondName, thirdName) == null;
    }

    /**
     * Удалить человека по id.
     *
     * @param id id пациента
     * @return состояние успещности удаления
     */
    public boolean deletePersonById(int id) {
        if (findInfoById(id) == null) {
            System.out.println(String.format("Нет пациента с id = %s", id));
            return false;
        }
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE id_person=?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Запись удалена");
            System.out.println("=========================");
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return findInfoById(id) == null;
    }

    /**
     * Изменяет поля записи о пациента
     *
     * @param person пациент
     * @return измененный пациент
     */
    public Person changePerson(Person person) throws Exception {
        if (findInfoById(person.getIdPerson()) == null) {
            throw new Exception(String.format("Нет пациента %s %s %s",
                    person.getName(),
                    person.getSecondName(),
                    person.getThirdName()));
        } else {
            String sqlQuery = "UPDATE " + TABLE_NAME + " SET name=?, " +
                    "second_name=?," +
                    "third_name=?" +
                    " WHERE id_person=?";
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sqlQuery);
                statement.setString(1, person.getName());
                statement.setString(2, person.getSecondName());
                statement.setString(3, person.getThirdName());
                statement.setInt(4, person.getIdPerson());
                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            }

        }
        return findInfoById(person.getIdPerson());
    }
}
