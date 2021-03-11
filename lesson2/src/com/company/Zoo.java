package com.company;

import com.company.Employee;

import java.util.Arrays;

public class Zoo {
     final Employee[] employees;
     Volyer[] volyers;
    public Zoo(Employee[] employees, Volyer[] volyers) {
        this.employees = Arrays.copyOf(employees, employees.length);
        this.volyers = Arrays.copyOf(volyers, volyers.length);
    }

    /**
     * Добавление животного в зоопарк
     * @param indexVolyer
     * @param indexEmployee
     * @param animal
     */
    public void addAnimal(int indexVolyer, int indexEmployee, Animal animal) {
        Employee emp = employees[indexEmployee];
        Volyer v = volyers[indexVolyer];
        v.addAnimal(animal);
    }

    /**
     * Удаление животного из зоопарка
     * @param name
     * @param indexVolyer
     */
    public void deleteAnimal(String name, int indexVolyer) {
        Volyer v = volyers[indexVolyer];
        v.deleteAnimalByName(name);
    }
}
