package ru.croc.javaschool.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.croc.javaschool.converter.model.Statistic;
import ru.croc.javaschool.model.Corona;
import ru.croc.javaschool.model.Person;
import ru.croc.javaschool.model.enums.StatusCorona;
import ru.croc.javaschool.service.CoronaService;
import ru.croc.javaschool.service.PersonService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Конвертер.
 */
public class Converter {
    /**
     * Получить объект статистики
     * @param coronaService сервис
     * @param dateStart дата начала
     * @param dateEnd дата конца
     * @return объект статистики
     */
    static public Statistic getStatisticBetweenDates(CoronaService coronaService,
                                                     LocalDate dateStart, LocalDate dateEnd) {
        Statistic statistic = new Statistic();
        List<Corona> coronaList = coronaService.findBetweenDates(dateStart, dateEnd);
        int countHealthy = 0;
        int countDiseased = 0;
        countHealthy = (int) coronaList.stream().filter((corona -> corona.getStatusCorona() == StatusCorona.HEALTHY))
                                          .count();
        countDiseased = (int) coronaList.stream().filter((corona -> corona.getStatusCorona() == StatusCorona.DISEASED))
                .count();
        statistic.setCountDiseased(countDiseased);
        statistic.setCountHealthy(countHealthy);
        statistic.setPercentage(countHealthy / (countDiseased + countHealthy));
        return statistic;
    }
    /**
     * Получить xml из объекта statistic
     * @param statistic объект класса Statistic
     * @return строка
     */
    static public String getXmlFromStatistic(Statistic statistic) throws JsonProcessingException {
        return (new JaxbConverter()).toXml(statistic);
    }
}
