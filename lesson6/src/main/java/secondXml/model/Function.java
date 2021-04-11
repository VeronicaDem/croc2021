package secondXml.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Функция человека в фильме.
 */
public class Function {
    /**
     * Название функции.
     */
    @XmlAttribute
    private String name;

    public Function(String name) {
        this.name = name;
    }
    public Function() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
