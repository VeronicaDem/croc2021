package secondXml;

import secondXml.model.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Люди
 */
@XmlRootElement(name="people")
public class People {
    /**
     * Список человек.
     */
    @XmlElement(name="person")
    private ArrayList<Person> people;

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public People(ArrayList<Person> people) {
        this.people = people;
    }

    public People() {
    }
}
