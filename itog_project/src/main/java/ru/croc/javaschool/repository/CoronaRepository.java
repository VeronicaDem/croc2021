package ru.croc.javaschool.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.javaschool.model.Corona;
import ru.croc.javaschool.model.enums.StatusCorona;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий информации о болезни пациентов коронавирусом.
 */
public class CoronaRepository {
    /**
     * Имя таблицы.
     */
    private static final String TABLE_NAME = "corona";
    /**
     * Таблица пациентов.
     */
    private static final String TABLE_PERSON_NAME = "person";
    /**
     * Хранилище.
     */
    private EmbeddedDataSource dataSource;
    public CoronaRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }
    /**
     * Метод инициализации БД.
     */
    private void initTable() {
        System.out.println(String.format("Start initializing %s table", TABLE_NAME));
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});

            if(resultSet.next()) {
                System.out.println("Table has already been initialized");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                        + TABLE_NAME
                        + " ("
                        + "id_person INTEGER NOT NULL REFERENCES " + TABLE_PERSON_NAME + "(id_person),"
                        + "id_corona INTEGER PRIMARY KEY " +
                                "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "date_corona DATE,"
                        + "status SMALLINT)"
                );
                System.out.println("Table was successfully initialized");
            }
        }catch(SQLException e) {
            System.out.println("Error occurred during table initializing: " + e.getMessage());
        } finally {
            System.out.println("=========================");
        }
    }
    /**
     * Метод поиска справки о болезни в промежуток от dateStart до dateEnd
     * @param dateStart дата начала промежутка
     * @param dateEnd дата конца промежутка
     * @return список справок
     */
    public List<Corona> findBetweenDates(LocalDate dateStart, LocalDate dateEnd) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE date_corona >= ? and date_corona <= ? ";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setDate(1, Date.valueOf(dateStart));
            statement.setDate(2, Date.valueOf(dateEnd));
            ResultSet resultSet = statement.executeQuery();
            List<Corona> coronaList = new ArrayList<>();
            while (resultSet.next()) {
                coronaList.add(
                        new Corona(
                                resultSet.getInt("id_person"),
                                resultSet.getInt("id_corona"),
                                resultSet.getDate("date_corona").toLocalDate(),
                                resultSet.getInt("status") == 1 ? StatusCorona.HEALTHY :
                                                                             StatusCorona.DISEASED
                        )
                );

            }
            return coronaList;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }
    /**
     * Метод поиска записей о болезни конкретного человека.
     * @param idPerson id человека
     * @return список всех записей о болезни человека
     */
    public List<Corona> findByIdPerson(int idPerson) {
        List<Corona> coronaList;
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id_person=?";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, idPerson);
            ResultSet resultSet = statement.executeQuery();
            coronaList = new ArrayList<>();
            while(resultSet.next()) {
                coronaList.add(
                       new Corona (resultSet.getInt("id_person"),
                        resultSet.getInt("id_corona"),
                        resultSet.getDate("date_corona").toLocalDate(),
                        resultSet.getInt("status") == 1 ? StatusCorona.HEALTHY :
                                StatusCorona.DISEASED
                       )
                );
            }
        } catch (Exception e) {
            coronaList = null;
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        if(coronaList.isEmpty()) coronaList = null;
        return coronaList;
    }
    /**
     * Метод удаления записи о болезни конкретного человека.
     * @param idPerson id человека
     * @return состояние успешности удаления
     */
    public boolean deleteCoronaByIdPerson(int idPerson) {
       if(findByIdPerson(idPerson) == null) {
           System.out.println(String.format("Записей о человеке с id=%s нет", idPerson));
           return false;
       }
      String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE id_person=?";
       try(Connection connection = dataSource.getConnection()) {
           PreparedStatement statement = connection.prepareStatement(sqlQuery);
           statement.setInt(1, idPerson);
           statement.execute();
           System.out.println("Запись удалена");
           System.out.println("=========================");
       } catch (Exception e) {
           System.out.println("Ошибка выполнения запроса: " + e.getMessage());
       }
       return findByIdPerson(idPerson) == null;
    }

    /**
     * Поиск записи конкретного больного в определенное время
     * @param idPerson
     * @param dateCorona
     * @return запись болезни
     */
    public Corona findByPersonAndDate(int idPerson, LocalDate dateCorona) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id_person=? AND date_corona=?";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, idPerson);
            statement.setDate(2, Date.valueOf(dateCorona));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Corona corona = new Corona(
                    resultSet.getInt("id_person"),
                    resultSet.getInt("id_corona"),
                    resultSet.getDate("date_corona").toLocalDate(),
                    resultSet.getInt("status") == 1 ? StatusCorona.HEALTHY :
                            StatusCorona.DISEASED
            );
            return corona;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            return null;
        }
    }
    /**
     * Обновление записи о болезни конкретного человека в определенное время
     * @param idPerson id человека
     * @param dateCorona время
     * @param statusCorona измененный статус болезни
     * @return обновленная запись
     */
    public Corona changeCorona(int idPerson, LocalDate dateCorona, StatusCorona statusCorona) throws Exception{
        if(findByPersonAndDate(idPerson, dateCorona) == null) {
            throw new Exception("Такой записи нет");
        }
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET status=?" +
                          " WHERE id_person=? AND date_corona=?";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, statusCorona == StatusCorona.HEALTHY ? 1 : 0);
            statement.setInt(2, idPerson);
            statement.setDate(3, Date.valueOf(dateCorona));
            statement.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return findByPersonAndDate(idPerson, dateCorona);
    }
    /**
     * Создать запись о болезни.
     * @param corona запись о болезни
     * @return новая запись
     */
    public Corona createNew(Corona corona) throws Exception{
        if(findByPersonAndDate(corona.getPersonId(), corona.getDate()) != null) {
            throw new Exception("Запись уже существует. Возможно Вы хотели изменить запись");
        }
        String sqlQuery = "INSERT INTO " + TABLE_NAME + "(id_person, date_corona, status) " +
                                                  "VALUES(?,?,?)";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, corona.getPersonId());
            statement.setDate(2, Date.valueOf(corona.getDate()));
            statement.setInt(3, corona.getStatusCorona() == StatusCorona.HEALTHY ? 1 : 0);
            statement.executeUpdate();
            System.out.println("Запись добавлена");
            System.out.println("=========================");
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            return null;
        }
        return findByPersonAndDate(corona.getPersonId(), corona.getDate());
    }
}
