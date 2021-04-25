package ru.croc.javaschool.service;

import ru.croc.javaschool.model.Person;
import ru.croc.javaschool.repository.CoronaRepository;
import ru.croc.javaschool.repository.PersonRepository;

import java.util.List;

/**
 * Сервис для PersonRepository
 * P.S. не делала тесты, так как функционал не изменяется.
 */
public class PersonService {
    /**
     * Репозиторий.
     */
    private final PersonRepository repository;
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }
    /**
     * Метод поиска всех пациентов (людей).
     * @return список всех пациентов
     */
    public List<Person> findAll() {
        return repository.findAll();
    }
    /**
     * Метод создания записи в БД о новом пациенте.
     *
     * @param person Пациент
     */
    public Person createNew(Person person) throws Exception{
        return repository.createNew(person);
    }
    /**
     * Получить пациента по id.
     * @param id id пациента
     * @return пациент
     */
    public Person findInfoById(int id) {
        return repository.findInfoById(id);
    }
    /**
     * Получить пациента по фио.
     * @param name имя пациента
     * @param secondName фамилия пациента
     * @param thirdName отчество пациента
     * @return пациент
     */
    public Person findInfoByFio(String name, String secondName, String thirdName) {
        return repository.findInfoByFio(name, secondName, thirdName);
    }
    /**
     * Удалить человека по fio.
     * @param name имя пациента.
     * @param secondName фамилия пациента.
     * @param thirdName отчество пациента.
     * @return состояние успешности удаления
     */
    public boolean deletePersonByFio(String name, String secondName, String thirdName) {
        return repository.deletePersonByFio(name, secondName, thirdName);
    }
    /**
     * Изменяет поля записи о пациента
     * @param person пациент
     * @return измененный пациент
     */
    public Person changePerson(Person person) throws Exception {
        return repository.changePerson(person);
    }
}
