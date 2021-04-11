package ru.croc.lesson7.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.lesson7.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий студентов
 */
public class StudentRepository {
    /**
     * Имя таблицы.
     */
    private static final String TABLE_NAME = "student";
    /**
     * Хранилище.
     */
    private EmbeddedDataSource dataSource;

    public StudentRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Метод инициализации БД.

     */
    private void initTable() {
        System.out.println(String.format("Start initializing %s table", TABLE_NAME));
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
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
                                + "id INTEGER PRIMARY KEY, "
                                + "fio VARCHAR(255), "
                                + "groupName INTEGER, "
                                + "dateOfBirth DATE,"
                                + "extracted SMALLINT,"
                                + "dateOfStart DATE"
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
     * Метод поиска всех студентов в БД.
     *
     * @return список всех добавленных студентов
     */
    public List<Student> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<Student> studentList = new ArrayList<>();
            while (resultSet.next()) {
                studentList.add(
                        new Student(
                                resultSet.getInt("id"),
                                resultSet.getString("fio"),
                                resultSet.getInt("groupName"),
                                resultSet.getDate("dateOfBirth").toLocalDate(),
                                resultSet.getInt("extracted") == 1,
                                resultSet.getDate("dateOfStart").toLocalDate()));
            }
            return studentList;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Метод создания записи в БД о новом студенте.
     *
     * @param student Студент
     */
    public Student createNew(Student student) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + "(id, fio, groupName, " +
                                                      "dateOfBirth, extracted, dateOfStart) " +
                                                      "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection()) {
             PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, student.getId());
            statement.setString(2, student.getFio());
            statement.setInt(3, student.getGroup());
            statement.setDate(4, Date.valueOf(student.getDateOfBirth()));
            statement.setInt(5, student.isExtracted() ? 1 : 0);
            statement.setDate(6, Date.valueOf(student.getDateOfStart()));
            statement.execute();
            System.out.println("Запись добавлена");
            System.out.println("=========================");
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            student = null;
        }
        return student;
    }
}
