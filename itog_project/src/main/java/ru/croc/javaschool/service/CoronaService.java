package ru.croc.javaschool.service;

import ru.croc.javaschool.model.Corona;
import ru.croc.javaschool.model.enums.StatusCorona;
import ru.croc.javaschool.repository.CoronaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 Сервис для CoronaRepository
 P.S. не делала тесты, так как функционал не изменяется.
 */
public class CoronaService {
    /**
     * Репозиторий.
     */
    private final CoronaRepository repository;
    public CoronaService(CoronaRepository repository) {
        this.repository = repository;
    }
    /**
     * Получение списка заболеваний в пределах по времени.
     * @param dateStart начальная дата
     * @param dateEnd конечная дата
     * @return список заболеваний
     */
    public List<Corona> findBetweenDates(LocalDate dateStart, LocalDate dateEnd) {
        return repository.findBetweenDates(dateStart, dateEnd);
    }
    /**
     * Метод поиска записей о болезни конкретного человека.
     * @param idPerson id человека
     * @return список всех записей о болезни человека
     */
    public List<Corona> findByIdPerson(int idPerson) {
        return repository.findByIdPerson(idPerson);
    }
    /**
     * Метод удаления записи о болезни конкретного человека.
     * @param idPerson id человека
     * @return состояние успешности удаления
     */
    public boolean deleteCoronaByIdPerson(int idPerson) {
        return repository.deleteCoronaByIdPerson(idPerson);
    }
    /**
     * Поиск записи конкретного больного в определенное время
     * @param idPerson
     * @param dateCorona
     * @return запись болезни
     */
    public Corona findByPersonAndDate(int idPerson, LocalDate dateCorona) {
       return repository.findByPersonAndDate(idPerson, dateCorona);
    }
    /**
     * Обновление записи о болезни конкретного человека в определенное время
     * @param idPerson id человека
     * @param dateCorona время
     * @param statusCorona измененный статус болезни
     * @return обновленная запись
     */
    public Corona changeCorona(int idPerson, LocalDate dateCorona, StatusCorona statusCorona) throws Exception {
        return repository.changeCorona(idPerson, dateCorona, statusCorona);
    }
    /**
     * Создать запись о болезни.
     * @param corona запись о болезни
     * @return новая запись
     */
    public Corona createNew(Corona corona) throws Exception{
        return repository.createNew(corona);
    }
}
