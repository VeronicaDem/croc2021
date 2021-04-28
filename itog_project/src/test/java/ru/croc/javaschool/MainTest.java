package ru.croc.javaschool;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.converter.ConverterDisease;
import ru.croc.javaschool.converter.model.StatisticDisease;
import ru.croc.javaschool.dbprovider.DataSourceProvider;
import ru.croc.javaschool.model.CoronaInfo;
import ru.croc.javaschool.model.Person;
import ru.croc.javaschool.model.enums.StatusCorona;
import ru.croc.javaschool.repository.CoronaInfoRepository;
import ru.croc.javaschool.repository.PersonRepository;
import ru.croc.javaschool.service.CoronaService;
import ru.croc.javaschool.service.PersonService;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Тест для главного сценария
 */
public class MainTest {
    DataSourceProvider dataSourceProvider = new DataSourceProvider();
    CoronaInfoRepository coronaInfoRepository = new CoronaInfoRepository(dataSourceProvider.getDataSource());
    CoronaService coronaService = new CoronaService(coronaInfoRepository);
    PersonRepository personRepository = new PersonRepository(dataSourceProvider.getDataSource());
    PersonService personService = new PersonService(personRepository);

    public MainTest() throws IOException {
    }
    @BeforeEach
    public void deletePatients2() {
        deletePatients();
    }
    @AfterEach
    public void deletePatients() {
        if (personService.findInfoByFio("Илья",
                "Васильев", "Васильевич") != null) {
            coronaService.deleteCoronaByPersonId(personService.findInfoByFio("Илья",
                    "Васильев", "Васильевич").getIdPerson());
            personService.deletePersonByFio("Илья", "Васильев", "Васильевич");
        }
        if (personService.findInfoByFio("Сергей",
                "Рубцов", "Евгеньевич") != null) {
            coronaService.deleteCoronaByPersonId(personRepository.findInfoByFio("Сергей",
                    "Рубцов", "Евгеньевич").getIdPerson());
            personService.deletePersonByFio("Сергей",
                    "Рубцов", "Евгеньевич");
        }
    }
    /**
     * Главный сценарий.
     */
    @Test
    public void mainTest() throws Exception {
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personService.createNew(person);
        CoronaInfo coronaInfo = new CoronaInfo(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        CoronaInfo coronaInfoAdded = coronaService.createNew(coronaInfo);

        Person person2 = new Person(
                "Сергей",
                "Рубцов", "Евгеньевич"
        );
        Person addedPerson2 = personService.createNew(person2);
        CoronaInfo coronaInfo2 = new CoronaInfo(
                addedPerson2.getIdPerson(),
                LocalDate.of(2020, 2, 2),
                StatusCorona.HEALTHY

        );
        CoronaInfo coronaInfoAdded2 = coronaService.createNew(coronaInfo2);

        StatisticDisease statisticDisease = ConverterDisease.getStatisticBetweenDates(coronaService,
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 2, 2)
        );
        String xml = ConverterDisease.getXmlFromStatistic(statisticDisease);
        System.out.println(xml);
        ConverterDisease.writeToFile(xml);
    }
}
