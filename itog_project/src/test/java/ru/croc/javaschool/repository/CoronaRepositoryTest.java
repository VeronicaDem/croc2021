package ru.croc.javaschool.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.dbprovider.DataSourceProvider;
import ru.croc.javaschool.model.Corona;
import ru.croc.javaschool.model.Person;
import ru.croc.javaschool.model.enums.StatusCorona;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Тест для CoronaRepository
 */
public class CoronaRepositoryTest {
    DataSourceProvider dataSourceProvider = new DataSourceProvider();
    CoronaRepository coronaRepository = new CoronaRepository(dataSourceProvider.getDataSource());
    PersonRepository personRepository = new PersonRepository(dataSourceProvider.getDataSource());
    public CoronaRepositoryTest() throws IOException {
    }
    /**
     * Тест для {@link CoronaRepository#createNew(Corona)}
     */
    @Test
    public void createNewTest() throws Exception {
        if(personRepository.findInfoByFio("Илья",
                "Васильев", "Васильевич") != null) {
            coronaRepository.deleteCoronaByIdPerson(personRepository.findInfoByFio("Илья",
                    "Васильев", "Васильевич").getIdPerson());
            personRepository.deletePersonByFio("Илья", "Васильев", "Васильевич");
        }
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        Corona corona = new Corona(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        Corona coronaAdded = coronaRepository.createNew(corona);
        Assertions.assertNotNull(coronaAdded);
        Assertions.assertEquals(corona.getPersonId(), coronaAdded.getPersonId());
        Assertions.assertEquals(corona.getDate(), coronaAdded.getDate());
        Assertions.assertEquals(corona.getStatusCorona(), coronaAdded.getStatusCorona());
    }
    /**
     * Тест для {@link CoronaRepository#findBetweenDates(LocalDate, LocalDate)}
     */
    @Test
    public void findBetweenDatesTest() throws Exception {
        if(personRepository.findInfoByFio("Илья",
                "Васильев", "Васильевич") != null) {
            coronaRepository.deleteCoronaByIdPerson(personRepository.findInfoByFio("Илья",
                    "Васильев", "Васильевич").getIdPerson());
            personRepository.deletePersonByFio("Илья", "Васильев", "Васильевич");
        }
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        Corona corona = new Corona(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        Corona coronaAdded = coronaRepository.createNew(corona);

        if(personRepository.findInfoByFio("Сергей",
                "Рубцов", "Евгеньевич") != null) {
            coronaRepository.deleteCoronaByIdPerson(personRepository.findInfoByFio("Сергей",
                    "Рубцов", "Евгеньевич").getIdPerson());
            personRepository.deletePersonByFio("Сергей",
                    "Рубцов", "Евгеньевич");
        }
        Person person2 = new Person(
                "Сергей",
                "Рубцов", "Евгеньевич"
        );
        Person addedPerson2 = personRepository.createNew(person2);
        Corona corona2 = new Corona(
                addedPerson2.getIdPerson(),
                LocalDate.of(2020, 2, 2),
                StatusCorona.HEALTHY

        );
        Corona coronaAdded2 = coronaRepository.createNew(corona2);

        List<Corona> coronaArrayList = coronaRepository.findBetweenDates(
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 2, 2));
        AtomicBoolean flag1 = new AtomicBoolean(false);
        AtomicBoolean flag2 = new AtomicBoolean(false);
        coronaArrayList.forEach((c) -> {
            if(c.equals(coronaAdded)) {
                flag1.set(true);
            }
            if(c.equals(coronaAdded2)) {
                flag2.set(true);
            }
        });
        Assertions.assertTrue(flag1.get() && flag2.get());
    }
    /**
     * Тест для {@link CoronaRepository#changeCorona(int, LocalDate, StatusCorona)}
     */
    @Test
    public void changeCoronaTest() throws Exception {
        if(personRepository.findInfoByFio("Илья",
                "Васильев", "Васильевич") != null) {
            coronaRepository.deleteCoronaByIdPerson(personRepository.findInfoByFio("Илья",
                    "Васильев", "Васильевич").getIdPerson());
            personRepository.deletePersonByFio("Илья", "Васильев", "Васильевич");
        }
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        Corona corona = new Corona(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        Corona coronaAdded = coronaRepository.createNew(corona);
        Corona changedCorona = coronaRepository.changeCorona(addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.DISEASED);
        Assertions.assertEquals(coronaAdded.getCoronaId(), changedCorona.getCoronaId());
        Assertions.assertEquals(coronaAdded.getPersonId(), changedCorona.getPersonId());
        Assertions.assertEquals(StatusCorona.DISEASED, changedCorona.getStatusCorona());
    }
    /**
     * Тест для {@link CoronaRepository#findByPersonAndDate(int, LocalDate)}
     */
    @Test
    public void findByPersonAndDateTest() throws Exception {
        if(personRepository.findInfoByFio("Илья",
                "Васильев", "Васильевич") != null) {
            coronaRepository.deleteCoronaByIdPerson(personRepository.findInfoByFio("Илья",
                    "Васильев", "Васильевич").getIdPerson());
            personRepository.deletePersonByFio("Илья", "Васильев", "Васильевич");
        }
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        Corona corona = new Corona(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        Corona coronaAdded = coronaRepository.createNew(corona);
        Corona coronaFound = coronaRepository.findByPersonAndDate(corona.getPersonId(), corona.getDate());
        Assertions.assertEquals(corona.getPersonId(), coronaFound.getPersonId());
        Assertions.assertEquals(corona.getDate(), coronaFound.getDate());
        Assertions.assertEquals(coronaAdded.getStatusCorona(), coronaFound.getStatusCorona());
        Assertions.assertEquals(coronaAdded.getCoronaId(), coronaFound.getCoronaId());
    }
    /**
     * Тест для {@link CoronaRepository#findByIdPerson(int)}
     */
    @Test
    public void findByIdPersonTest() throws Exception {
        if(personRepository.findInfoByFio("Илья",
                "Васильев", "Васильевич") != null) {
            coronaRepository.deleteCoronaByIdPerson(personRepository.findInfoByFio("Илья",
                    "Васильев", "Васильевич").getIdPerson());
            personRepository.deletePersonByFio("Илья", "Васильев", "Васильевич");
        }
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        Corona corona = new Corona(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        Corona coronaAdded = coronaRepository.createNew(corona);
        List<Corona> coronaFound = coronaRepository.findByIdPerson(corona.getPersonId());
        AtomicBoolean flag = new AtomicBoolean(false);
        coronaFound.forEach((c) -> {
            if(c.equals(coronaAdded)) {
                flag.set(true);
            }
        });
        Assertions.assertTrue(flag.get());
    }
    /**
     * Тест для {@link CoronaRepository#deleteCoronaByIdPerson(int)}
     */
    @Test
    public void deleteCoronaByIdPersonTest() throws Exception {
        if(personRepository.findInfoByFio("Илья",
                "Васильев", "Васильевич") != null) {
            coronaRepository.deleteCoronaByIdPerson(personRepository.findInfoByFio("Илья",
                    "Васильев", "Васильевич").getIdPerson());
            personRepository.deletePersonByFio("Илья", "Васильев", "Васильевич");
        }
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        Corona corona = new Corona(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        coronaRepository.createNew(corona);
        Assertions.assertTrue(coronaRepository.deleteCoronaByIdPerson(addedPerson.getIdPerson()));
        Assertions.assertFalse(coronaRepository.deleteCoronaByIdPerson(addedPerson.getIdPerson()));
    }

}
