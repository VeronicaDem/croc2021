package ru.croc.javaschool.model;

import ru.croc.javaschool.model.enums.StatusCorona;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Информация о заболевании конкретного человека.
 */
public class CoronaInfo {
    /**
     * id человека
     */
    private int personId;
    /**
     * id записи о состоянии больного коронавирусом.
     */
    private int coronaId;
    /**
     * Дата записи.
     */
    private LocalDate date;
    /**
     * Статус больного.
     */
    private StatusCorona statusCorona;

    public CoronaInfo(LocalDate date, StatusCorona statusCorona) {
        this.date = date;
        this.statusCorona = statusCorona;
    }

    public CoronaInfo(int personId, LocalDate date, StatusCorona statusCorona) {
        this(date, statusCorona);
        this.personId = personId;
    }

    public CoronaInfo(int personId, int coronaId, LocalDate date, StatusCorona statusCorona) {
        this(date, statusCorona);
        this.personId = personId;
        this.coronaId = coronaId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getCoronaId() {
        return coronaId;
    }

    public void setCoronaId(int coronaId) {
        this.coronaId = coronaId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public StatusCorona getStatusCorona() {
        return statusCorona;
    }

    public void setStatusCorona(StatusCorona statusCorona) {
        this.statusCorona = statusCorona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoronaInfo coronaInfo = (CoronaInfo) o;
        return personId == coronaInfo.personId
                && coronaId == coronaInfo.coronaId
                && Objects.equals(date, coronaInfo.date)
                && statusCorona == coronaInfo.statusCorona;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, coronaId, date, statusCorona);
    }
}
