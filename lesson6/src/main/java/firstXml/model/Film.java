package firstXml.model;


import javax.xml.bind.annotation.*;

import java.util.ArrayList;
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

    public Film(String title, String description, ArrayList<Person> screenwriters, ArrayList<Person> directors) {
        this.title = title;
        this.description = description;
        this.screenwriters = screenwriters;
        this.directors = directors;
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
