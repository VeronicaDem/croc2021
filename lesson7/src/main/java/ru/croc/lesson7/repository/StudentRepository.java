package ru.croc.lesson7.repository;

import com.sun.security.jgss.GSSUtil;
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
    /**
     * Получить студента по id
     * @param id id Студента
     * @return студент
     */
    public Student showInfoById(int id) {
        String sqlQuery = "SELECT id, fio, groupName, " +
                "dateOfBirth, extracted, dateOfStart " + "FROM " + TABLE_NAME +
                " WHERE id=?";
        Student student = null;
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            student = new Student(
                    rs.getInt("id"),
                    rs.getString("fio"),
                    rs.getInt("groupName"),
                    rs.getDate("dateOfBirth").toLocalDate(),
                    rs.getInt("extracted") == 1,
                    rs.getDate("dateOfStart").toLocalDate());
        } catch(Exception e) {
            System.out.println("Ошибка выполнения запроса " + e.getMessage());
        }
        return student;
    }
    /**
     * Удалить студента по id
     * @param id id Студента
     * @return состояние успешности удаления
     */
    public boolean deleteStudentById(int id) {
        if(showInfoById(id) == null) {
            System.out.println("Студента с id="+ id + " нет");
            return false;
        }
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE id =?";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Запись удалена");
            System.out.println("=========================");
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        if(showInfoById(id) == null) {
            return true;
        }
        return false;
    }
    /**
     * Изменяет поля записи о студенте
     * @param student студент с измененными полями
     * @return измененный студент
     */
    public Student changeStudent(Student student) throws Exception {
        if(showInfoById(student.getId()) == null) {
            throw new Exception("Нет такого студента");
        }
        else {
            String sqlQuery = "UPDATE " + TABLE_NAME + " SET fio=?, " +
                                                             "groupName=?, " +
                                                              "dateOfBirth=?," +
                                                              "extracted=?," +
                                                              "dateOfStart=?" +
                              " WHERE id=?";
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sqlQuery);
                statement.setString(1, student.getFio());
                statement.setInt(2, student.getGroup());
                statement.setDate(3, Date.valueOf(student.getDateOfBirth()));
                statement.setInt(4, student.isExtracted() ? 1: 0);
                statement.setDate(5, Date.valueOf(student.getDateOfStart()));
                statement.setInt(6, student.getId());
                statement.executeUpdate();
            }
            catch(Exception e) {
                System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            }
        }
        return showInfoById(student.getId());
    }
}
