import com.fasterxml.jackson.core.JsonProcessingException;
import firstXml.Films;
import firstXml.model.Film;
import secondXml.People;
import secondXml.model.Function;
import secondXml.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Тот самый класс, переводящий из первого представления во второй
 * Конвертер
 */
public class Converter {
    /**
     * Конвертация из класса фильмов в класс люди
     * @param films фильмы
     * @return люди
     */
    static People convertToPeople(Films films) {
        People people = new People();
        Map<String, Person> personNameToPerson = new HashMap<>();
        ArrayList<Person> personArrayList = new ArrayList<>();
        Map<String, ArrayList<secondXml.model.Film>> personNameToFilm = new HashMap<>();
        int i = 0;
        for(Film film : films.getFilms()) {
            for(firstXml.model.Person person : film.getDirectors()) {
                secondXml.model.Film newFilm = null;
                if(personNameToFilm.get(person.getName()) == null) {
                    ArrayList<secondXml.model.Film> filmArrayList = new ArrayList<>();
                    newFilm = new secondXml.model.Film();
                    newFilm.setTitle(film.getTitle());
                    filmArrayList.add(newFilm);
                    personNameToFilm.put(person.getName(), filmArrayList);
                }
                else {
                    ArrayList<secondXml.model.Film> filmArrayList = personNameToFilm.get(person.getName());
                    for(secondXml.model.Film film1 : filmArrayList) {
                        if(film1.getTitle().equals(film.getTitle())) {
                            newFilm = film1;

                        }
                    }
                    if(newFilm == null) {
                        newFilm = new secondXml.model.Film();
                        newFilm.setTitle(film.getTitle());
                        filmArrayList.add(newFilm);
                    }
                }
                Person personSecondXml = null;
                if(personNameToPerson.get(person.getName()) == null) {
                    personSecondXml = new Person();
                    personSecondXml.setName(person.getName());
                    personNameToPerson.put(person.getName(), personSecondXml);
                }
                else {
                    personSecondXml = personNameToPerson.get(person.getName());
                }

                if(!newFilm.existFunction("Режиссер")) newFilm.addFunction(new Function("Режиссер"));
                if(!personSecondXml.getFilms().contains(newFilm)) {
                    personSecondXml.getFilms().add(newFilm);
                }

                if(!personArrayList.contains(personSecondXml)) {
                    personArrayList.add(personSecondXml);
                }

            }
            for(firstXml.model.Person person : film.getScreenwriters()) {
                Person personSecondXml = null;
                secondXml.model.Film newFilm = null;
                if(personNameToFilm.get(person.getName()) == null) {
                    ArrayList<secondXml.model.Film> filmArrayList = new ArrayList<>();
                    newFilm = new secondXml.model.Film();
                    newFilm.setTitle(film.getTitle());
                    filmArrayList.add(newFilm);
                    personNameToFilm.put(person.getName(), filmArrayList);
                }
                else {
                    ArrayList<secondXml.model.Film> filmArrayList = personNameToFilm.get(person.getName());
                    for(secondXml.model.Film film1 : filmArrayList) {
                        if(film1.getTitle().equals(film.getTitle())) {
                            newFilm = film1;
                        }
                    }
                    if(newFilm == null) {
                        newFilm = new secondXml.model.Film();
                        newFilm.setTitle(film.getTitle());
                        filmArrayList.add(newFilm);
                    }
                }
                if(personNameToPerson.get(person.getName()) == null) {
                    personSecondXml = new Person();
                    personSecondXml.setName(person.getName());
                    personNameToPerson.put(person.getName(), personSecondXml);
                }
                else {
                    personSecondXml = personNameToPerson.get(person.getName());
                }
                if(!newFilm.existFunction("Сценарист")) newFilm.addFunction(new Function("Сценарист"));
                if(!personSecondXml.getFilms().contains(newFilm)) {
                    personSecondXml.getFilms().add(newFilm);
                }
                if(!personArrayList.contains(personSecondXml)) {
                    personArrayList.add(personSecondXml);
                }

            }

        }
        people.setPeople(personArrayList);
        return people;
    }

    /**
     * Получить xml из объекта people
     * @param people объект класса People
     * @return строка
     */
    static String getXmlFromPeople(People people) throws JsonProcessingException {
        return (new JaxbConverter()).toXml(people);
    }
}
