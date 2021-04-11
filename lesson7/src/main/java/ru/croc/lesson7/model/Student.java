package ru.croc.lesson7.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Студент.
 */
public class Student {
    /**
     * Индентификатор студента.
     */
    private Integer id;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", group=" + group +
                ", dateOfBirth=" + dateOfBirth +
                ", extracted=" + extracted +
                ", dateOfStart=" + dateOfStart +
                '}';
    }

    /**
     * Фио.
     */
    private String fio;
    /**
     * Номер группы.
     */
    private int group;
    /**
     * Дата рождения.
     */
    private LocalDate dateOfBirth;
    /**
     * Отчислен или нет.
     */
    private boolean extracted = false;
    /**
     * Дата поступления.
     */
    private LocalDate dateOfStart;

    public Student(int id, String fio, int group, LocalDate dateOfBirth,
                   boolean extracted, LocalDate dateOfStart) {
        this.fio = fio;
        this.group = group;
        this.dateOfBirth = dateOfBirth;
        this.extracted = extracted;
        this.dateOfStart = dateOfStart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isExtracted() {
        return extracted;
    }

    public void setExtracted(boolean extracted) {
        this.extracted = extracted;
    }

    public LocalDate getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(LocalDate dateOfStart) {
        this.dateOfStart = dateOfStart;
    }
}
