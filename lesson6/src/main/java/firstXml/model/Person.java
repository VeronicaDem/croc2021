package firstXml.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Человек
 */
public class Person {
    /**
     * Имя
     */
    @XmlAttribute(name = "name")
    private String name;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
