package ru.croc.javaschool;

import org.junit.jupiter.api.Test;
import ru.croc.javaschool.converter.Converter;
import ru.croc.javaschool.converter.model.Statistic;
import ru.croc.javaschool.dbprovider.DataSourceProvider;
import ru.croc.javaschool.model.Corona;
import ru.croc.javaschool.model.Person;
import ru.croc.javaschool.model.enums.StatusCorona;
import ru.croc.javaschool.repository.CoronaRepository;
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
    CoronaRepository coronaRepository = new CoronaRepository(dataSourceProvider.getDataSource());
    CoronaService coronaService = new CoronaService(coronaRepository);
    PersonRepository personRepository = new PersonRepository(dataSourceProvider.getDataSource());
    PersonService personService = new PersonService(personRepository);
    public MainTest() throws IOException {
    }

    /**
     * Главный сценарий.
     */
    @Test
    public void mainTest() throws Exception {
        if(personService.findInfoByFio("Илья",
                "Васильев", "Васильевич") != null) {
            coronaService.deleteCoronaByIdPerson(personService.findInfoByFio("Илья",
                    "Васильев", "Васильевич").getIdPerson());
            personService.deletePersonByFio("Илья", "Васильев", "Васильевич");
        }
        Person person = new Person(
                "Илья",
                "Васильев",
                "Васильевич"
        );
        Person addedPerson = personService.createNew(person);
        Corona corona = new Corona(
                addedPerson.getIdPerson(),
                LocalDate.of(2020, 1, 2),
                StatusCorona.HEALTHY

        );
        Corona coronaAdded = coronaService.createNew(corona);

        if(personService.findInfoByFio("Сергей",
                "Рубцов", "Евгеньевич") != null) {
            coronaService.deleteCoronaByIdPerson(personRepository.findInfoByFio("Сергей",
                    "Рубцов", "Евгеньевич").getIdPerson());
            personService.deletePersonByFio("Сергей",
                    "Рубцов", "Евгеньевич");
        }
        Person person2 = new Person(
                "Сергей",
                "Рубцов", "Евгеньевич"
        );
        Person addedPerson2 = personService.createNew(person2);
        Corona corona2 = new Corona(
                addedPerson2.getIdPerson(),
                LocalDate.of(2020, 2, 2),
                StatusCorona.HEALTHY

        );
        Corona coronaAdded2 = coronaService.createNew(corona2);

        Statistic statistic = Converter.getStatisticBetweenDates(coronaService,
                LocalDate.of(2020, 1, 2),
                LocalDate.of(2020, 2, 2)
                );
        System.out.println(Converter.getXmlFromStatistic(statistic));
    }
}
