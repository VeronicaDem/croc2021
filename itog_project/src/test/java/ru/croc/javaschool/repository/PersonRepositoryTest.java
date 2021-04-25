package ru.croc.javaschool.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.dbprovider.DataSourceProvider;
import ru.croc.javaschool.model.Corona;
import ru.croc.javaschool.model.Person;

import java.io.IOException;
import java.util.List;

/**
 * Тесты для репозитория PersonRepository
 */
public class PersonRepositoryTest {
    DataSourceProvider dataSourceProvider = new DataSourceProvider();
    PersonRepository personRepository = new PersonRepository(dataSourceProvider.getDataSource());
    CoronaRepository coronaRepository = new CoronaRepository(dataSourceProvider.getDataSource());
    public PersonRepositoryTest() throws IOException {
    }

    /**
     * Тест для {@link PersonRepository#createNew(Person)}
     */
    @Test
    public void createNewTest() throws Exception {
        personRepository.deletePersonByFio("Василий", "Лукичев", "Васильевич");
        Person person = new Person(
                "Василий",
                "Лукичев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        Assertions.assertNotNull(addedPerson);
        Assertions.assertEquals(person.getName(), addedPerson.getName());
        Assertions.assertEquals(person.getSecondName(), addedPerson.getSecondName());
        Assertions.assertThrows(Exception.class , () -> personRepository.createNew(person));
    }
    /**
     * Тест для {@link PersonRepository#findInfoByFio(String, String, String)}
     */
    @Test
    public void findInfoByIdTest() throws Exception {
        personRepository.deletePersonByFio("Василий", "Лукичев", "Васильевич");
        Person person = new Person(
                "Василий",
                "Лукичев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        int id = addedPerson.getIdPerson();
        Person foundPerson = personRepository.findInfoById(id);
        Assertions.assertNotNull(foundPerson);
        Assertions.assertEquals(id, foundPerson.getIdPerson());
        Assertions.assertEquals(addedPerson.getName(), foundPerson.getName());
        Assertions.assertEquals(person.getSecondName(), foundPerson.getSecondName());

    }
    /**
     * Тест для {@link PersonRepository#findInfoByFio(String, String, String)}
     */
    @Test
    public void findInfoByFioTest() throws Exception {
        personRepository.deletePersonByFio("Василий", "Лукичев", "Васильевич");
        Person person = new Person(
                "Василий",
                "Лукичев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        Person foundPerson = personRepository.findInfoByFio("Василий",
                                                       "Лукичев",
                                                         "Васильевич");
        Assertions.assertNotNull(foundPerson);
        Assertions.assertEquals(addedPerson.getIdPerson(), foundPerson.getIdPerson());
        Assertions.assertEquals(addedPerson.getName(), foundPerson.getName());
        Assertions.assertEquals(addedPerson.getSecondName(), foundPerson.getSecondName());
        Assertions.assertEquals(addedPerson.getThirdName(), foundPerson.getThirdName());
    }
    /**
     * Тест для {@link PersonRepository#changePerson(Person)}
     */
    @Test
    public void changePersonTest() throws Exception {
        personRepository.deletePersonByFio("Василий", "Лукичев", "Васильевич");
        Person person = new Person(
                "Василий",
                "Лукичев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        Person changedPerson = personRepository.changePerson(new Person
                ("Вася", "Лукичев", "Васильевич", addedPerson.getIdPerson()));
        Assertions.assertNotNull(changedPerson);
        Assertions.assertEquals(addedPerson.getIdPerson(), changedPerson.getIdPerson());
        Assertions.assertEquals("Вася", changedPerson.getName());
        Assertions.assertEquals("Лукичев", changedPerson.getSecondName());
        Assertions.assertEquals("Васильевич", changedPerson.getThirdName());

    }
    /**
     * Тест для {@link PersonRepository#deletePersonByFio(String, String, String)}
     */
    @Test
    public void deletePersonByFioTest() throws Exception {
        personRepository.deletePersonByFio("Василий", "Лукичев", "Васильевич");
        Person person = new Person(
                "Василий",
                "Лукичев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        Assertions.assertTrue(personRepository.deletePersonByFio("Василий",
                "Лукичев", "Васильевич"));
        Assertions.assertFalse(personRepository.deletePersonByFio("Василий",
                "Лукичев", "Васильевич"));
    }
    /**
     * Тест для {@link PersonRepository#deletePersonById(int)}
     */
    @Test
    public void deletePersonByIdTest() throws Exception {
        personRepository.deletePersonByFio("Василий", "Лукичев", "Васильевич");
        Person person = new Person(
                "Василий",
                "Лукичев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        Assertions.assertTrue(personRepository.deletePersonById(addedPerson.getIdPerson()));
        Assertions.assertFalse(personRepository.deletePersonById(addedPerson.getIdPerson()));
    }
}
