package secondXml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Фильм.
 */
public class Film {
    /**
     * Название фильма.
     */
    @XmlAttribute
    private String title;

    /**
     * Функции
     */
    @XmlElementWrapper(name="functions")
    @XmlElement
    private ArrayList<Function> functions = new ArrayList<>();


    public boolean existFunction(String name) {
        for(Function function : functions) {
            if(function.getName().equals(name)) return true;
        }
        return false;
    }
    public void addFunction(Function function) {
        functions.add(function);
    }
    public Film() {
    }

    public Film(String title, ArrayList<Function> functions) {
        this.title = title;
        this.functions = functions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(ArrayList<Function> functions) {
        this.functions = functions;
    }
}
