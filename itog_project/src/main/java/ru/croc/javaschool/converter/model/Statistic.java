package ru.croc.javaschool.converter.model;
import javax.xml.bind.annotation.*;
/**
 * Статистика.
 */
@XmlRootElement(name="statistic")
public class Statistic {
    /**
     * Количество выздоровивших.
     */
    @XmlElement(name="выздоровившие")
    private int countHealthy;
    /**
     * Количество заболевших.
     */
    @XmlElement(name="заболевшие")
    private int countDiseased;
    /**
     * Процентное соотношение выздоровивших к полному числу пациентов.
     */
    @XmlElement(name="соотношение выздоровивших")
    private double percentage;

    public Statistic(int countHealthy, int countDiseased, double percentage) {
        this.countHealthy = countHealthy;
        this.countDiseased = countDiseased;
        this.percentage = percentage;
    }
    public Statistic() {

    }

    public int getCountHealthy() {
        return countHealthy;
    }

    public void setCountHealthy(int countHealthy) {
        this.countHealthy = countHealthy;
    }

    public int getCountDiseased() {
        return countDiseased;
    }

    public void setCountDiseased(int countDiseased) {
        this.countDiseased = countDiseased;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}