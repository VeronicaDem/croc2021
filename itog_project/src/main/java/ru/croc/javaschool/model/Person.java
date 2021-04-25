package ru.croc.javaschool.model;

/**
 * Человек.
 */
public class Person {
    /**
     * id человека из базы данных
     */
    private int idPerson;
    /**
     * Имя человека.
     */
    private String name;
    /**
     * Фамилия человека.
     */
    private String secondName;
    /**
     * Отчество человека.
     */
    private String thirdName;

    public Person(String name, String secondName, String thirdName) {
        this.name = name;
        this.secondName = secondName;
        this.thirdName = thirdName;
    }
    public Person(String name, String secondName, String thirdName, int idPerson) {
        this(name, secondName, thirdName);
        this.idPerson = idPerson;
    }
    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPeron(int idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idPerson +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", thirdName='" + thirdName + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }
}
