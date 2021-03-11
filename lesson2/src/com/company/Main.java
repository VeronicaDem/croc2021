package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Employee [] employees = {
                new Employee("Никита", "Ивченко"),
                new Employee("Вероника","Иванова")
        };

        Animal[] animals = {
                new Animal(new String[] {},"Тигр Вася" ),
                new Animal(new String[] {"Чесотка"},"Бегемот Викентий" ),
                new Animal("Сова Лаки", new String [] {}, "08.03.2021")

    };
        Animal [] animals1 = {
                new Animal(new String[]{}, "Рысь Илюша")
        };
        Volyer [] volyers = {
                new Volyer(employees[0], animals),
                new Volyer(employees[1], animals1)
        };
        Zoo zoo = new Zoo(employees, volyers);
        // Появление и исчезновение животного
        zoo.addAnimal(1, 0, new Animal(new String[] {},"Дятел Тра-та-та"));
        zoo.deleteAnimal("Рысь Илюша", 1);

        // Добавить записи о болезнях животного
        volyers[0].addIll("Лихорадка", 0);
        // Добавляет записи об уборке вольера
        volyers[0].clean();
        // Добавить записи о кормлении животного
        volyers[0].addFeed(0);

    }
}
