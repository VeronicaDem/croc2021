package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Volyer {
    private Employee employee;
    private Animal [] animals;
    private boolean isClean;
    private Date date;
    private String report = "";
    public Volyer(Employee employee, Animal[] animals) {
        this.employee = employee;
        this.animals = animals;
        this.isClean = false;

    }
    public void addFeed(int indexAnimal) {
        animals[indexAnimal].feed();
    }
    /**
     * Расширяющий конструктор с определением времени ближайшей уборки вольера
     * Если между уборкой и сегодняшним днем разница меньше, чем 4 дня, то будем считать
     * что вольер чист
     * @param employee
     * @param animals
     * @param formatDate
     */
    /**
     *
     * @param employee
     * @param animals
     * @param formatDate - последняя дата кормления животного
     */
    public Volyer(Employee employee, Animal[] animals, String formatDate) {
        this(employee, animals);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date now = new Date();
        try {
            date = dateFormat.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isClean = (int)(now.getTime() - date.getTime())/  (24 * 60 * 60 * 1000) < 4;
        if (isClean) {
            addDateToReport();
        }
    }
    /**
     * Добавляет болезнь к животному и печатает их
     */
    public void addIll(String ill, int indexAnimal) {
        animals[indexAnimal].addIllness(ill);
        animals[indexAnimal].addDateToReport();
    }

    /**
     * Добавляет записи об уборке вольера
     */
    public String addDateToReport() {
        report += date.toString() + "\n";
        return report;
    }

    /**
     * Чистит вольер и выводит записи
     */
    public void clean() {
        date = new Date();
        isClean = true;
        System.out.println(addDateToReport());
    }

    /**
     * Уборка вольера
     * Проверка на выполнение условия уборки в разнице дней
     * Пересчет isClean - флага на чистоту вольера
     * @return новый isClean
     */
    public boolean isClean() {
        Date now = new Date();
        isClean = (int)(now.getTime() - date.getTime())/  (24 * 60 * 60 * 1000) < 4;
        return isClean;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Animal[] getAnimals() {
        return Arrays.copyOf(animals, animals.length);
    }

    public void deleteAnimalByName(String name) {
        Animal [] animals = new Animal[this.animals.length - 1];
        int j = 0;
        for(int i = 0; i < this.animals.length; i++) {
            if (this.animals[i].getName().equals(name)) {
                continue;
            }
            else animals[j++] = this.animals[i];
        }
        this.animals = animals;
    }
    public void setAnimals(Animal[] animals) {
        this.animals = Arrays.copyOf(animals, animals.length);
    }
    public void deleteAnimal() {

    }
    /**
     * Добавление животного
     * @param animal
     */
    public void addAnimal(Animal animal) {
        this.animals = Arrays.copyOf(animals, animals.length + 1);
        this.animals[animals.length - 1] = animal;
    }
}
