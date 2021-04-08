import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.models.Film;
import ru.croc.models.Person;

import java.io.IOException;
import java.util.ArrayList;

public class CommonTest {
    /**
     * 1) Тестирование правильной растановки аннотаций
     */
    @Test
    public void correctAnnotationTest() throws IOException {
        JaxbConverter converter = new JaxbConverter();
        Films films = converter.fromFileToObject("xml.txt", Films.class);

    }
    /**
     * 2) Тестирование правильной расстановки аннотаций
     */
    @Test
    public void correctAnnotationTest2() throws IOException {
        /*JaxbConverter converter = new JaxbConverter();
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
        Films films = new Films(filmArrayList);
        String xml = converter.toXml(films);
        System.out.println(xml);

        Assertions.assertEquals(films, converter.fromXml(xml, Films.class));
        xml = converter.toXml(film1);
        System.out.println(xml);
*/
    }
}
