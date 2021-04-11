package firstXml;

import firstXml.model.Film;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.*;

/**
 * Фильмы.
 */
@XmlRootElement(name="films")
public class Films {
    /**
     * Список фильмов.
     */

    @XmlElement(name="film")
    List<Film> films = new ArrayList<>();
    public Films() {

    }
    public Films(ArrayList<Film> films) {
        this.films = films;
    }
    public Films(List<Film> films) {
        this.films = films;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
