package ru.croc.javaschool.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.croc.javaschool.converter.model.StatisticDisease;
import ru.croc.javaschool.model.CoronaInfo;
import ru.croc.javaschool.model.enums.StatusCorona;
import ru.croc.javaschool.service.CoronaService;

import java.time.LocalDate;
import java.util.List;

/**
 * Конвертер.
 */
public class ConverterDisease {
    /**
     * Получить объект статистики
     *
     * @param coronaService сервис
     * @param dateStart     дата начала
     * @param dateEnd       дата конца
     * @return объект статистики
     */
    static public StatisticDisease getStatisticBetweenDates(CoronaService coronaService,
                                                            LocalDate dateStart, LocalDate dateEnd) {
        StatisticDisease statisticDisease = new StatisticDisease();
        List<CoronaInfo> coronaInfoList = coronaService.findBetweenDates(dateStart, dateEnd);
        int countHealthy = 0;
        int countDiseased = 0;
        countHealthy = (int) coronaInfoList.stream()
                .filter((corona -> corona.getStatusCorona() == StatusCorona.HEALTHY))
                .count();
        countDiseased = (int) coronaInfoList.stream()
                .filter((corona -> corona.getStatusCorona() == StatusCorona.DISEASED))
                .count();
        statisticDisease.setCountDiseased(countDiseased);
        statisticDisease.setCountHealthy(countHealthy);
        statisticDisease.setPercentage(countHealthy / (countDiseased + countHealthy));
        return statisticDisease;
    }

    /**
     * Получить xml из объекта statistic
     *
     * @param statisticDisease объект класса Statistic
     * @return строка
     */
    static public String getXmlFromStatistic(StatisticDisease statisticDisease) throws JsonProcessingException {
        return (new JaxbConverter()).toXml(statisticDisease);
    }

    /**
     * Записать xml в файл
     */
    static public void writeToFile(String xml) {
        (new JaxbConverter()).writeXmlToFile(xml);
    }
}
