package secondXml.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Человек.
 */
public class Person {
    /**
     * Имя человека.
     */
    @XmlElement
    private String name;

    @XmlElementWrapper(name="films")
    @XmlElement(name="film")
    private ArrayList<Film> films = new ArrayList<>();


    public Person(String name, ArrayList<Film> films) {
        this.name = name;
        this.films = films;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }
}
