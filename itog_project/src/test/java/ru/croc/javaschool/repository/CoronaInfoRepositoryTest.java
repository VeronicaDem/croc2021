package ru.croc.javaschool.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.dbprovider.DataSourceProvider;
import ru.croc.javaschool.model.CoronaInfo;
import ru.croc.javaschool.model.Person;
import ru.croc.javaschool.model.enums.StatusCorona;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Тест для CoronaRepository
 */
public class CoronaInfoRepositoryTest {
    DataSourceProvider dataSourceProvider = new DataSourceProvider();
    CoronaInfoRepository coronaInfoRepository = new CoronaInfoRepository(dataSourceProvider.getDataSource());
    PersonRepository personRepository = new PersonRepository(dataSourceProvider.getDataSource());

    public CoronaInfoRepositoryTest() throws IOException {
    }
    @AfterEach
    public void deletePatients() {
        if (personRepository.findInfoByFio("Илья",
                "Васильев", "Васильевич") != null) {
            coronaInfoRepository.deleteCoronaByPersonId(personRepository.findInfoByFio("Илья",
                    "Васильев", "Васильевич").getIdPerson());
            personRepository.deletePersonByFio("Илья", "Васильев", "Васильевич");
        }
        if (personRepository.findInfoByFio("Сергей",
                "Рубцов", "Евгеньевич") != null) {
            coronaInfoRepository.deleteCoronaByPersonId(personRepository.findInfoByFio("Сергей",
                    "Рубцов", "Евгеньевич").getIdPerson());
            personRepository.deletePersonByFio("Сергей",
                    "Рубцов", "Евгеньевич");
        }
    }
    @BeforeEach
    public void deletePatientsBefore() {
        deletePatients();
    }
    /**
     * Тест для {@link CoronaInfoRepository#createNew(CoronaInfo)}
     */
    @Test
    public void createNewTest() throws Exception {
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        CoronaInfo coronaInfo = new CoronaInfo(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        CoronaInfo coronaInfoAdded = coronaInfoRepository.createNew(coronaInfo);
        Assertions.assertNotNull(coronaInfoAdded);
        Assertions.assertEquals(coronaInfo.getPersonId(), coronaInfoAdded.getPersonId());
        Assertions.assertEquals(coronaInfo.getDate(), coronaInfoAdded.getDate());
        Assertions.assertEquals(coronaInfo.getStatusCorona(), coronaInfoAdded.getStatusCorona());
    }

    /**
     * Тест для {@link CoronaInfoRepository#findBetweenDates(LocalDate, LocalDate)}
     */
    @Test
    public void findBetweenDatesTest() throws Exception {
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        CoronaInfo coronaInfo = new CoronaInfo(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        CoronaInfo coronaInfoAdded = coronaInfoRepository.createNew(coronaInfo);

        Person person2 = new Person(
                "Сергей",
                "Рубцов", "Евгеньевич"
        );
        Person addedPerson2 = personRepository.createNew(person2);
        CoronaInfo coronaInfo2 = new CoronaInfo(
                addedPerson2.getIdPerson(),
                LocalDate.of(2020, 2, 2),
                StatusCorona.HEALTHY

        );
        CoronaInfo coronaInfoAdded2 = coronaInfoRepository.createNew(coronaInfo2);

        List<CoronaInfo> coronaInfoArrayList = coronaInfoRepository.findBetweenDates(
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 2, 2));
        AtomicBoolean flag1 = new AtomicBoolean(false);
        AtomicBoolean flag2 = new AtomicBoolean(false);
        coronaInfoArrayList.forEach((c) -> {
            if (c.equals(coronaInfoAdded)) {
                flag1.set(true);
            }
            if (c.equals(coronaInfoAdded2)) {
                flag2.set(true);
            }
        });
        Assertions.assertTrue(flag1.get() && flag2.get());
    }

    /**
     * Тест для {@link CoronaInfoRepository#changeCorona(int, LocalDate, StatusCorona)}
     */
    @Test
    public void changeCoronaTest() throws Exception {
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        CoronaInfo coronaInfo = new CoronaInfo(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        CoronaInfo coronaInfoAdded = coronaInfoRepository.createNew(coronaInfo);
        CoronaInfo changedCoronaInfo = coronaInfoRepository.changeCorona(addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.DISEASED);
        Assertions.assertEquals(coronaInfoAdded.getCoronaId(), changedCoronaInfo.getCoronaId());
        Assertions.assertEquals(coronaInfoAdded.getPersonId(), changedCoronaInfo.getPersonId());
        Assertions.assertEquals(StatusCorona.DISEASED, changedCoronaInfo.getStatusCorona());
    }

    /**
     * Тест для {@link CoronaInfoRepository#findByPersonAndDate(int, LocalDate)}
     */
    @Test
    public void findByPersonAndDateTest() throws Exception {
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        CoronaInfo coronaInfo = new CoronaInfo(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        CoronaInfo coronaInfoAdded = coronaInfoRepository.createNew(coronaInfo);
        CoronaInfo coronaInfoFound = coronaInfoRepository.findByPersonAndDate(coronaInfo.getPersonId(), coronaInfo.getDate());
        Assertions.assertEquals(coronaInfo.getPersonId(), coronaInfoFound.getPersonId());
        Assertions.assertEquals(coronaInfo.getDate(), coronaInfoFound.getDate());
        Assertions.assertEquals(coronaInfoAdded.getStatusCorona(), coronaInfoFound.getStatusCorona());
        Assertions.assertEquals(coronaInfoAdded.getCoronaId(), coronaInfoFound.getCoronaId());
    }

    /**
     * Тест для {@link CoronaInfoRepository#findByPersonId(int)}
     */
    @Test
    public void findByPersonIdTest() throws Exception {
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        CoronaInfo coronaInfo = new CoronaInfo(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        CoronaInfo coronaInfoAdded = coronaInfoRepository.createNew(coronaInfo);
        List<CoronaInfo> coronaInfoFound = coronaInfoRepository.findByPersonId(coronaInfo.getPersonId());
        AtomicBoolean flag = new AtomicBoolean(false);
        coronaInfoFound.forEach((c) -> {
            if (c.equals(coronaInfoAdded)) {
                flag.set(true);
            }
        });
        Assertions.assertTrue(flag.get());
    }

    /**
     * Тест для {@link CoronaInfoRepository#deleteCoronaByPersonId(int)}
     */
    @Test
    public void deleteCoronaByPersonIdTest() throws Exception {
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personRepository.createNew(person);
        CoronaInfo coronaInfo = new CoronaInfo(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        coronaInfoRepository.createNew(coronaInfo);
        Assertions.assertTrue(coronaInfoRepository.deleteCoronaByPersonId(addedPerson.getIdPerson()));
        Assertions.assertFalse(coronaInfoRepository.deleteCoronaByPersonId(addedPerson.getIdPerson()));
    }

}
