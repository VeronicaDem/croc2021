import com.fasterxml.jackson.core.JsonProcessingException;
import firstXml.Films;
import firstXml.model.Person;
import org.junit.jupiter.api.Test;
import firstXml.model.Film;
import secondXml.People;


import java.io.IOException;
import java.util.ArrayList;

public class CommonTest {
    /**
     * Тестирование конвертатора
     */
    @Test
    public void converterTestFromPeopleToXml() throws IOException {
        JaxbConverter converter = new JaxbConverter();
        Films films = converter.fromFileToObject("src/main/java/resources/films.xml", Films.class);
        People people = Converter.convertToPeople(films);
        String xml = Converter.getXmlFromPeople(people);
        System.out.println(xml);
        // проверяю на наличие исключений
    }
    /**
     * Тестирование правильной расстановки аннотаций
     */
    @Test
    public void correctAnnotationTest() throws IOException {
        /*JaxbConverter converter = new JaxbConverter();
        firstXml.model.Films films = new firstXml.model.Films();
        String xml = converter.toXml(films);
        System.out.println(xml);

        Assertions.assertEquals(films, converter.fromXml(xml, firstXml.model.Films.class));
*/
        JaxbConverter converter = new JaxbConverter();
        Person person1 = new Person("Белоснежка");
        Person person2 = new Person("Семь гномов");
        ArrayList<Person> screenwriters = new ArrayList<>();
        screenwriters.add(person1);
        screenwriters.add(person2);
        ArrayList<Person> directors = new ArrayList<>();
        directors.add(person1);
        directors.add(person2);
        Film film1 = new Film("Красавица и чудовище",
                "мультфильм",
                screenwriters,
                directors
        );
        ArrayList<Film> filmArrayList = new ArrayList<Film>();
        filmArrayList.add(film1);
        Films films = new Films(filmArrayList);
        String xml = converter.toXml(films);
       // проверяю на наличие исключений

    }

    /**
     * Проверка работы функции {@link Converter#getXmlFromPeople(People)} и {@link Converter#convertToPeople(Films)}
     */
    @Test
    public void converterTest() throws JsonProcessingException {
        Person person1 = new Person("Белоснежка");
        Person person2 = new Person("Семь гномов");
        ArrayList<Person> screenwriters = new ArrayList<>();
        screenwriters.add(person1);
        screenwriters.add(person2);
        ArrayList<Person> directors = new ArrayList<>();
        directors.add(person1);
        directors.add(person2);
        Film film1 = new Film("Красавица и чудовище",
                "мультфильм",
                screenwriters,
                directors
        );
        ArrayList<Film> filmArrayList = new ArrayList<Film>();
        filmArrayList.add(film1);
        Films films = new Films(filmArrayList);
        People people = Converter.convertToPeople(films);
        String xml = Converter.getXmlFromPeople(people);
        System.out.println(xml);
    }
}
