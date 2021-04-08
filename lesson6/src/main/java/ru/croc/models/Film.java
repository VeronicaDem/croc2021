package ru.croc.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.util.List;

/**
 * Фильм.
 */
public class Film {
    /**
     * Заголовок
     */
    @XmlElement(name="title")
    private String title;
    /**
     * Описание
     */
    @XmlElement(name="description")
    private String description;
    /**
     * Сценаристы
     */
    @XmlElementWrapper(name="screenwriters")
    @XmlElement(name="screenwriter")
    private List<Person> screenwriters;
    /**
     * Режиссеры
     */
    @XmlElementWrapper(name="directors")
    @XmlElement(name="director")
    private List<Person> directors;

    public Film() {

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getScreenwriters() {
        return screenwriters;
    }

    public void setScreenwriters(List<Person> screenwriters) {
        this.screenwriters = screenwriters;
    }

    public List<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = directors;
    }
}
