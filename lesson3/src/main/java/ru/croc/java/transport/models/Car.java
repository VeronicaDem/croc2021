package ru.croc.java.transport.models;

import ru.croc.java.transport.WithEngineTransport;
import ru.croc.java.transport.enums.*;

/**
 * Легковая машина
 */
public class Car extends WithEngineTransport {
    /**
     * Количество мест. Пассажирских или грузовых
     */
    int countPlaces;
    /**
     * Пробег.
     */
    double probeg;
    /**
     * Габариты.Например, A, B, C, D, E
     */
    String classSize;

    public Car(int year, double maxVelocity, double capacity, double cost, String color, String brand, double power,
               int quantity, boolean isOK, Fuel typeFuel, boolean isRented, ContainsType containsType,
               String concreteFuel,
               Bodywork bodywork, boolean left, int countPlaces, double probeg, String classSize) {
        super(TransportType.TERRESTRIAL, UseType.INDIVIDUAL, year, maxVelocity, capacity, cost, color, brand, power,
                quantity, isOK, typeFuel, isRented, containsType, concreteFuel, bodywork, left);
        this.countPlaces = countPlaces;
        this.probeg = probeg;
        this.classSize = classSize;
    }

    @Override
    public String toString() {
        return "Car{" +
                "countPlaces=" + countPlaces +
                ", probeg=" + probeg +
                ", classSize='" + classSize + '\'' +
                '}';
    }

    public int getCountPlaces() {
        return countPlaces;
    }

    public void setCountPlaces(int countPlaces) {
        this.countPlaces = countPlaces;
    }

    public double getProbeg() {
        return probeg;
    }

    public void setProbeg(double probeg) {
        this.probeg = probeg;
    }

    public String getClassSize() {
        return classSize;
    }

    public void setClassSize(String classSize) {
        this.classSize = classSize;
    }
}
